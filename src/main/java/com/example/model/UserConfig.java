package com.example.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName USER_CONFIG
 */
@TableName(value ="USER_CONFIG")
@Data
public class UserConfig implements Serializable {
    /**
     * 
     */
    @TableId
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 权限()
     */
    private String role;

    /**
     * 是否启用0:代表false、1:代表true
     */
    private Integer isenabled;

    /**
     * 添加时间
     */
    private Date createAt;

    /**
     * 更新时间
     */
    private Date updateAt;

    /**
     * 会员等级
     */
    private String userLevel;

    /**
     * 备注
     */
    private String desc;

    /**
     * IP地址
     */
    private String ip;

    /**
     * MAC地址
     */
    private String mac;

    /**
     * 付款方式
     */
    private String payType;

    /**
     * 信用卡类型
     */
    private String creditCardsType;

    /**
     * 信用卡卡号
     */
    private String creditCardsNumber;

    /**
     * 信用卡日期
     */
    private String creditCardsDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        UserConfig other = (UserConfig) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getRole() == null ? other.getRole() == null : this.getRole().equals(other.getRole()))
            && (this.getIsenabled() == null ? other.getIsenabled() == null : this.getIsenabled().equals(other.getIsenabled()))
            && (this.getCreateAt() == null ? other.getCreateAt() == null : this.getCreateAt().equals(other.getCreateAt()))
            && (this.getUpdateAt() == null ? other.getUpdateAt() == null : this.getUpdateAt().equals(other.getUpdateAt()))
            && (this.getUserLevel() == null ? other.getUserLevel() == null : this.getUserLevel().equals(other.getUserLevel()))
            && (this.getDesc() == null ? other.getDesc() == null : this.getDesc().equals(other.getDesc()))
            && (this.getIp() == null ? other.getIp() == null : this.getIp().equals(other.getIp()))
            && (this.getMac() == null ? other.getMac() == null : this.getMac().equals(other.getMac()))
            && (this.getPayType() == null ? other.getPayType() == null : this.getPayType().equals(other.getPayType()))
            && (this.getCreditCardsType() == null ? other.getCreditCardsType() == null : this.getCreditCardsType().equals(other.getCreditCardsType()))
            && (this.getCreditCardsNumber() == null ? other.getCreditCardsNumber() == null : this.getCreditCardsNumber().equals(other.getCreditCardsNumber()))
            && (this.getCreditCardsDate() == null ? other.getCreditCardsDate() == null : this.getCreditCardsDate().equals(other.getCreditCardsDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getRole() == null) ? 0 : getRole().hashCode());
        result = prime * result + ((getIsenabled() == null) ? 0 : getIsenabled().hashCode());
        result = prime * result + ((getCreateAt() == null) ? 0 : getCreateAt().hashCode());
        result = prime * result + ((getUpdateAt() == null) ? 0 : getUpdateAt().hashCode());
        result = prime * result + ((getUserLevel() == null) ? 0 : getUserLevel().hashCode());
        result = prime * result + ((getDesc() == null) ? 0 : getDesc().hashCode());
        result = prime * result + ((getIp() == null) ? 0 : getIp().hashCode());
        result = prime * result + ((getMac() == null) ? 0 : getMac().hashCode());
        result = prime * result + ((getPayType() == null) ? 0 : getPayType().hashCode());
        result = prime * result + ((getCreditCardsType() == null) ? 0 : getCreditCardsType().hashCode());
        result = prime * result + ((getCreditCardsNumber() == null) ? 0 : getCreditCardsNumber().hashCode());
        result = prime * result + ((getCreditCardsDate() == null) ? 0 : getCreditCardsDate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", role=").append(role);
        sb.append(", isenabled=").append(isenabled);
        sb.append(", createAt=").append(createAt);
        sb.append(", updateAt=").append(updateAt);
        sb.append(", userLevel=").append(userLevel);
        sb.append(", desc=").append(desc);
        sb.append(", ip=").append(ip);
        sb.append(", mac=").append(mac);
        sb.append(", payType=").append(payType);
        sb.append(", creditCardsType=").append(creditCardsType);
        sb.append(", creditCardsNumber=").append(creditCardsNumber);
        sb.append(", creditCardsDate=").append(creditCardsDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}