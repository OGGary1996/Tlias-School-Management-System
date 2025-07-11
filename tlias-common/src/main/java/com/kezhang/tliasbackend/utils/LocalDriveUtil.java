package com.kezhang.tliasbackend.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
public class LocalDriveUtil {
    // 配置文件注入
//    @Value("${uploads.path}")
//    private String uploadsPath;
//
//    @Value("${uploads.url-prefix}")
//    private String uploadsUrlPrefix;
    private final LocalDriveUtilProperties localDriveUtilProperties;
    public LocalDriveUtil(LocalDriveUtilProperties localDriveUtilProperties) {
        this.localDriveUtilProperties = localDriveUtilProperties;
    }


    /*
    * 文件本地存储方法
    * 调用者：并非由Controller调用，因为删除文件属于附属操作，直接由Service层的方法调用
    * 流程：
    * 1. 获取上传的文件的原始文件名和扩展名
    * 2. 生成一个新的文件名，使用UUID来避免文件名冲突
    * 3. 结合每个业务逻辑，将文件拼接上新的路径
    * 4. 保存文件到本地目录
    * 5. 返回文件的访问路径给前端
    * */
    public String saveFile(MultipartFile file, String servicePath) throws IOException {
        String uploadsPath = localDriveUtilProperties.getPath();
        String uploadsUrlPrefix = localDriveUtilProperties.getUrlPrefix();

        // 1. 获取文件的原始文件名以及文件的扩展名
        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        log.info("File original filename: {}, extension name: {}", originalFilename, fileExtension);
        // 2. 生成一个新的文件名，使用UUID来避免文件名冲突
        String newFilename = java.util.UUID.randomUUID().toString() + fileExtension;
        // 3. 拼接上路径变量
        String filePath = uploadsPath + servicePath + newFilename;
        log.info("File will be saved to: {}", filePath);
        // 4. 保存文件到本地目录
            // 首先判断路径是否存在，不存在就创建
        File fileToSave = new File(filePath);
        if (fileToSave.getParentFile() != null && !fileToSave.getParentFile().exists()) {
            boolean dirCreated = fileToSave.getParentFile().mkdirs();
            log.info("Directory created: {}", fileToSave.getParentFile().getAbsolutePath());
            if (!dirCreated) {
                log.error("Failed to create directory: {}", fileToSave.getParentFile().getAbsolutePath());
                throw new RuntimeException("Failed to create directory for file storage.");
            }
        }
            // 保存文件到本地目录
        file.transferTo(fileToSave);
        log.info("File saved successfully: {}", fileToSave.getAbsolutePath());
        // 5. 返回文件的访问路径给前端
        return uploadsUrlPrefix + servicePath + newFilename;
    }

    /*
    * 文件删除方法
    * 流程：
    *  1. 获取要删除的文件url（从Controller）
    *  2. 删除文件需要的路径为真实的物理路径，所以需要删除http前缀，补充上uploadsPath物理路径前缀
    *  3. 删除文件
    * */
    public void deleteFile(String fileUrl) {
        String uploadsPath = localDriveUtilProperties.getPath();
        String uploadsUrlPrefix = localDriveUtilProperties.getUrlPrefix();
    
        String filePath = uploadsPath + fileUrl.substring(uploadsUrlPrefix.length());
        log.info("File to be deleted: {}", filePath);
        
        File fileToDelete = new File(filePath);
        if (fileToDelete.exists()) {
            // 添加权限检查，java可能没有权限删除本地文件，所以需要检查
            if (!fileToDelete.canWrite()) {
                log.error("No permission to delete file: {}", filePath);
                throw new RuntimeException("No permission to delete file. Please check file permissions.");
            }
            
            boolean deleted = fileToDelete.delete();
            if (deleted) {
                log.info("File deleted successfully: {}", filePath);
            } else {
                log.error("Failed to delete file: {}", filePath);
                throw new RuntimeException("Failed to delete file. Please check file permissions.");
            }
        } else {
            log.warn("File not found for deletion: {}", filePath);
        }
    }
}
