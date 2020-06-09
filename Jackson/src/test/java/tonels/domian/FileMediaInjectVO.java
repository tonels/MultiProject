package tonels.domian;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.base.Objects;

import java.time.LocalDateTime;

/**
 * 介质流水VO
 */

public class FileMediaInjectVO {

    // 请求流水ID
    private String requestNo;

    // 请求时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime requestDate;

    // 请求受理状态
    private String requestStatus;

    // 文件ID
    private Long fileId;

    // 服务接口ID
    private Long apiId;

    // 服务接口名称
    private String apiName;

    // 原始URL
    private String originalUrl;

    // 最终中心URL
    private String filePath;

    // 文件状态
    private String fileStatus;

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Long getApiId() {
        return apiId;
    }

    public void setApiId(Long apiId) {
        this.apiId = apiId;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileStatus() {
        return fileStatus;
    }

    public void setFileStatus(String fileStatus) {
        this.fileStatus = fileStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileMediaInjectVO that = (FileMediaInjectVO) o;
        return Objects.equal(requestNo, that.requestNo) &&
                Objects.equal(requestDate, that.requestDate) &&
                Objects.equal(requestStatus, that.requestStatus) &&
                Objects.equal(fileId, that.fileId) &&
                Objects.equal(apiId, that.apiId) &&
                Objects.equal(apiName, that.apiName) &&
                Objects.equal(originalUrl, that.originalUrl) &&
                Objects.equal(filePath, that.filePath) &&
                Objects.equal(fileStatus, that.fileStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(requestNo, requestDate, requestStatus, fileId, apiId, apiName, originalUrl, filePath, fileStatus);
    }
}
