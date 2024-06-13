    package kr.happytravel.erp.mypage.model;

    import com.fasterxml.jackson.annotation.JsonFormat;
    import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
    import com.fasterxml.jackson.annotation.JsonInclude;
    import com.fasterxml.jackson.databind.PropertyNamingStrategy;
    import com.fasterxml.jackson.databind.annotation.JsonNaming;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import java.util.Date;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public class NoticeModel {
        private String empId; // EMP_ID
        private String empName; // EMP_NAME
        private String deptCode; //    DEPT_CODE
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        private Date creationDate; //   CREATION_DATE
        private String title;   //  TITLE
        private String content;
    }
