package com.teboz.biz.page;

/**
 * 〈一句话功能简述〉<br> 
 * 返回到页面状态以及消息
 *
 * @author 于龙
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class RetMsg {
    /** status */
    private String status ;
    /** message*/
    private String message;
    
    /**
     * 功能描述: <br>
     * 创建对象
     *
     * @param status 状态
     * @param message 消息
     * @return 对象
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static RetMsg createRetMsg(String status,String message){
        RetMsg retMsg = new RetMsg();
        retMsg.setStatus(status);
        retMsg.setMessage(message);
        return retMsg;
    }
    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }
    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }
    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
}
