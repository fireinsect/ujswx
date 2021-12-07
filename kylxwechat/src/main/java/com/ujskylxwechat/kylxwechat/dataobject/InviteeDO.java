package com.ujskylxwechat.kylxwechat.dataobject;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InviteeDO {

    private Long projectId;

    /**
     * 参与者1的学号
     */

    private String invitee1Id;

    /**
     * 参与者1的姓名
     */

    private String invitee1Name;

    /**
     * 参与者2的学号
     */
    private String invitee2Id;

    /**
     * 参与者2的姓名
     */

    private String invitee2Name;

    /**
     * 参与者3的学号
     */
    private String invitee3Id;

    /**
     * 参与者3的姓名
     */

    private String invitee3Name;

    private LocalDateTime gmtCreated;

    private LocalDateTime gmtModified;
}
