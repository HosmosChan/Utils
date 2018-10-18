/*
 * copywrite 2015-2020 智慧享联
 * 不能修改和删除上面的版权声明
 * 此代码属于数据与信息中心部门编写，在未经允许的情况下不得传播复制
 */

/**
 * copywrite 2015-2020 金地物业
 * 不能修改和删除上面的版权声明
 * 此代码属于数据与信息中心部门编写，在未经允许的情况下不得传播复制
 * UserInfo.java
 * 2015年10月21日 上午10:27:08
 * taojinsha
 */
package com.gemdale.user.model;

import java.io.Serializable;

/**
 * TODO:
 *
 * @author taojinsha 2015年10月21日 上午10:27:08
 */
public class UserInfo implements Serializable {
    private long userId = 0;
    private String mobile;
    private String nickName;
    private String userName;
    private String birthday;
    private String certificateId;// 身份证
    private String sessionId;
    private String uniqueId;
    private String headImgUrl;
    // private String projectId;
    // private String projectCode;
    // private String projectName;
    // private String houseCode;
    private String sex;
    private String individualSign;// 个性签名
    private String mainHouseName; // 主房屋名称
    private String mainCheckStatus;// 主房屋审核状态  01:待审核    02：审核通过   03：审核不通过
    private String mainRelationType;// 主客房关系
    private String mainHouseCode;// 主房屋号
    private String mainProjectCode;// 主项目编号
    private String mainProjectName;// 主项目名称
    private String mainBuildingCode;// 主楼栋
    private String mainBuildingName; // 主楼栋名称
    private String status;
    private String lastMsgId;
    private String lastAnnoId;
    private String lastParcelId;
    private String mainControlFlag;
    private String parcelAgr;
    // 云对讲参数
    private String TalkbackUrl;
    private String TalkbackToSipCode;
    private String TalkbackSipPwd;
    // 云对讲参数 end
    //是否在浏览其他社区 true 是
    private boolean isBrowsed;
    //暂存浏览其他社区
    private String BrowsedCommunityName;
    private String BrowsedCommunityCode;
    // 是否是游客
    private boolean isTourist;

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public boolean isBrowsed() {
        return isBrowsed;
    }

    public void setBrowsed(boolean browsed) {
        isBrowsed = browsed;
    }

    public void setBrowsedCommunityName(String browsedCommunityName) {
        BrowsedCommunityName = browsedCommunityName;
    }

    public String getBrowsedCommunityName() {
        return BrowsedCommunityName;
    }

    public String getBrowsedCommunityCode() {
        return BrowsedCommunityCode;
    }

    public void setBrowsedCommunityCode(String browsedCommunityCode) {
        BrowsedCommunityCode = browsedCommunityCode;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getTalkbackUrl() {
        return TalkbackUrl;
    }

    public void setTalkbackUrl(String talkbackUrl) {
        TalkbackUrl = talkbackUrl;
    }

    public String getTalkbackToSipCode() {
        return TalkbackToSipCode;
    }

    public void setTalkbackToSipCode(String talkbackToSipCode) {
        TalkbackToSipCode = talkbackToSipCode;
    }

    public String getTalkbackSipPwd() {
        return TalkbackSipPwd;
    }

    public void setTalkbackSipPwd(String talkbackSipPwd) {
        TalkbackSipPwd = talkbackSipPwd;
    }

    /**
     * @return the parcelAgr
     */
    public String getParcelAgr() {
        return parcelAgr;
    }

    /**
     * @param parcelAgr the parcelAgr to set
     */
    public void setParcelAgr(String parcelAgr) {
        this.parcelAgr = parcelAgr;
    }

    public String getLastParcelId() {
        return lastParcelId;
    }

    public void setLastParcelId(String lastParcelId) {
        this.lastParcelId = lastParcelId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserName() {
        if (userName == null) {
            return "";
        }
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(String certificateId) {
        this.certificateId = certificateId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIndividualSign() {
        return individualSign;
    }

    public void setIndividualSign(String individualSign) {
        this.individualSign = individualSign;
    }

    public String getMainHouseName() {
        return mainHouseName;
    }

    public void setMainHouseName(String mainHouseName) {
        this.mainHouseName = mainHouseName;
    }

    public String getMainCheckStatus() {
        return mainCheckStatus;
    }

    public void setMainCheckStatus(String mainCheckStatus) {
        this.mainCheckStatus = mainCheckStatus;
    }

    public String getMainRelationType() {
        return mainRelationType;
    }

    public void setMainRelationType(String mainRelationType) {
        this.mainRelationType = mainRelationType;
    }

    public String getMainHouseCode() {
        return mainHouseCode;
    }

    public void setMainHouseCode(String mainHouseCode) {
        this.mainHouseCode = mainHouseCode;
    }

    public String getMainProjectCode() {
        return mainProjectCode;
    }

    public void setMainProjectCode(String mainProjectCode) {
        this.mainProjectCode = mainProjectCode;
    }

    public String getMainProjectName() {
        if (mainProjectName == null) {
            return "";
        }
        return mainProjectName;
    }

    public void setMainProjectName(String mainProjectName) {
        this.mainProjectName = mainProjectName;
    }

    public String getMainBuildingCode() {
        return mainBuildingCode;
    }

    public void setMainBuildingCode(String mainBuildingCode) {
        this.mainBuildingCode = mainBuildingCode;
    }

    public String getMainBuildingName() {
        return mainBuildingName;
    }

    public void setMainBuildingName(String mainBuildingName) {
        this.mainBuildingName = mainBuildingName;
    }

    public boolean isTourist() {
        return isTourist;
    }

    public void setTourist(boolean tourist) {
        isTourist = tourist;
    }

    /**
     * TODO:
     *
     * @return //00:已删除 01：审核中 02：已审核 03：审核失败
     */
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLastMsgId() {
        return lastMsgId;
    }

    public void setLastMsgId(String lastMsgId) {
        this.lastMsgId = lastMsgId;
    }

    public String getLastAnnoId() {
        return lastAnnoId;
    }

    public void setLastAnnoId(String lastAnnoId) {
        this.lastAnnoId = lastAnnoId;
    }

    public String getMainControlFlag() {
        return mainControlFlag;
    }

    public void setMainControlFlag(String mainControlFlag) {
        this.mainControlFlag = mainControlFlag;
    }

    /**
     * 是否可以报事 TODO:
     *
     * @return
     */
    public boolean canAddReport() {
        if (this.mainCheckStatus != null && this.mainCheckStatus.equals("02")) {
//            if (this.nickName != null) {
//                return true;
//            }
            return true;
        }
        return false;
    }

    /**
     * 是否可以查询物业费 TODO:
     *
     * @return
     */
    public boolean canQueryProperties() {
        if (this.mainCheckStatus != null && this.mainCheckStatus.equals("02")) {
            return true;
        }
        return false;
    }

    /**
     * 是否打开开门功能
     * TODO:
     *
     * @return
     */
    public boolean canOpenDoor() {
        if (this.mainControlFlag != null && this.mainControlFlag.length() > 1 && this.mainControlFlag.substring(0, 1).equals("1")) {
            return true;
        }
        return false;
    }

    /**
     * 是否打开邮包功能
     * TODO:
     *
     * @return
     */
    public boolean canPackage() {
        if (this.mainControlFlag != null && this.mainControlFlag.length() > 2 && this.mainControlFlag.substring(1, 2).equals("1")) {
            return true;
        }
        return false;
    }

    /**
     * 是否打开保险功能
     * TODO:
     *
     * @return
     */
    public boolean canInsurance() {
        if (this.mainControlFlag != null && this.mainControlFlag.length() > 3 && this.mainControlFlag.substring(2, 3).equals("1")) {
            return true;
        }
        return false;
    }

    /**
     * 是否打开贷款功能
     * TODO:
     *
     * @return
     */
    public boolean canCredit() {
        if (this.mainControlFlag != null && this.mainControlFlag.length() > 4 && this.mainControlFlag.substring(3, 4).equals("1")) {
            return true;
        }
        return false;
    }

    /**
     * 是否打开邻家特工功能
     * TODO:
     *
     * @return
     */
    public boolean canHouseKeeping() {
        if (this.mainControlFlag != null && this.mainControlFlag.length() > 5 && this.mainControlFlag.substring(4, 5).equals("1")) {
            return true;
        }
        return false;
    }

    public boolean canHelper() {
        if (this.mainControlFlag != null && this.mainControlFlag.length() > 6 && this.mainControlFlag.substring(5, 6).equals("1")) {
            return true;
        }
        return false;
    }

    public boolean canOnlineWork() {
        if (this.mainControlFlag != null && this.mainControlFlag.length() > 7 && this.mainControlFlag.substring(6, 7).equals("1")) {
            return true;
        }
        return false;
    }

    /**
     * 获得用户地址
     *
     * @return
     */
    public String getAdress() {
        return getMainProjectName() + getMainBuildingName() + getMainHouseName();
    }
}