package com.face;

/**
 * 常量类
 */
public class Constant {
    public static final String MESSAGE_RECEIVED_ACTION_ADD_FRIENDS_APPLY_MAIN =
            "com.bc.wechat.MESSAGE_RECEIVED_ACTION_ADD_FRIENDS_APPLY_MAIN";
    public static final String MESSAGE_RECEIVED_ACTION_ADD_FRIENDS_APPLY_NEW_FRIENDS_MSG =
            "com.bc.wechat.MESSAGE_RECEIVED_ACTION_ADD_FRIENDS_APPLY_NEW_FRIENDS_MSG";

    public static final String MESSAGE_RECEIVED_ACTION_ADD_FRIENDS_ACCEPT_MAIN =
            "com.bc.wechat.MESSAGE_RECEIVED_ACTION_ADD_FRIENDS_ACCEPT_MAIN";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

    public static String PICTURE_DIR = "sdcard/wechat_/pictures/";

    public static final String BASE_URL = "http://49.4.25.11:8081/";
    public static final String BASE_URL_PICTURE = "http://img2.woyaogexing.com/";

    public static final String FILE_UPLOAD_URL = BASE_URL + "files";
    public static final String FILE_BASE_URL = "http://49.4.25.11:9999/wechat_file/";

    public static final String USER_SEX_MALE = "1";
    public static final String USER_SEX_FEMALE = "2";

    public static final String IS_NOT_FRIEND = "0";
    public static final String IS_FRIEND = "1";

    public static final Integer FRIEND_APPLY_STATUS_ACCEPT = 1;

    // 用于推送的业务类型
    /**
     * 好友申请
     */
    public static final String PUSH_SERVICE_TYPE_ADD_FRIENDS_APPLY = "ADD_FRIENDS_APPLY";

    /**
     * 好友接收
     */
    public static final String PUSH_SERVICE_TYPE_ADD_FRIENDS_ACCEPT = "ADD_FRIENDS_ACCEPT";

    public static final String MSG_TYPE_TEXT = "text";
    public static final String MSG_TYPE_IMAGE = "image";
    public static final String MSG_TYPE_LOCATION = "location";
    public static final String MSG_TYPE_VOICE = "voice";
    public static final String MSG_TYPE_CUSTOM = "custom";
    public static final String MSG_TYPE_SYSTEM = "eventNotification";

    public static final String TARGET_TYPE_SINGLE = "single";
    public static final String TARGET_TYPE_GROUP = "group";
    public static final String TARGET_TYPE_CHATROOM = "chatroom";

    public static final int DEFAULT_PAGE_SIZE = 10;

    // 创建群聊方式
    public static final String CREATE_GROUP_TYPE_FROM_NULL = "1";

    public static final String CREATE_GROUP_TYPE_FROM_SINGLE = "2";

    public static final String CREATE_GROUP_TYPE_FROM_GROUP = "3";

    // 好友来源
    /**
     * 来自手机号搜索
     */
    public static final String FRIENDS_SOURCE_BY_PHONE = "1";

    /**
     * 来自微信号搜索
     */
    public static final String FRIENDS_SOURCE_BY_WX_ID = "2";

    /**
     * 朋友权限（所有权限：聊天、朋友圈、微信运动等）
     * default
     */
    public static final String RELA_AUTH_ALL = "0";

    /**
     * 朋友权限（仅聊天）
     */
    public static final String RELA_AUTH_ONLY_CHAT = "1";

    /**
     * 朋友圈和视频动态-可以看我
     * default
     */
    public static final String RELA_CAN_SEE_ME = "0";

    /**
     * 朋友圈时视频动态-不让他看我
     */
    public static final String RELA_NOT_SEE_ME = "1";

    /**
     * 朋友圈和视频动态-可以看他
     * default
     */
    public static final String RELA_CAN_SEE_HIM = "0";

    /**
     * 朋友圈时视频动态-不看他
     */
    public static final String RELA_NOT_SEE_HIM = "1";

    /**
     * 非星标好友
     */
    public static final String RELA_IS_NOT_STAR_FRIEND = "0";

    /**
     * 星标好友
     */
    public static final String RELA_IS_STAR_FRIEND = "1";

    /**
     * 星标好友分组title
     */
    public static final String STAR_FRIEND = "星标朋友";
}
