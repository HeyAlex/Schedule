package com.hey.mietunoff.mietunofficial.groups;

import com.hey.mietunoff.mietunofficial.util.BasePresenter;

/**
 * Created by mac on 09.05.17.
 */

public interface GroupsPresenter extends BasePresenter<GroupsView> {

    /**
     * Show downloaded groups
     */
    void showGroups();

    /**
     * Delete downloaded schedule
     *
     * @param groupName associated with schedule
     */
    void deleteGroup(String groupName);

    /**
     * Add new static shortcut on home screen
     *
     * @param groupName associated with schedule
     */
    void addNewStaticShortcut(String groupName);

    void requestWidget(String groupName);
}