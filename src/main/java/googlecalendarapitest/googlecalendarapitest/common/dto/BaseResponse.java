package googlecalendarapitest.googlecalendarapitest.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseResponse<T> {

    private int status;
    private T data;

    public static <T> BaseResponse<T> created() {
        return new BaseResponse<>(201, null);
    }
}
