package com.ldg.main.payload.request;

import javax.validation.constraints.NotEmpty;

public class ChangePasswordRequest {
    
    @NotEmpty
    private String oldPassword;
    @NotEmpty
    private String newPassword;
    @NotEmpty
    private String newPasswordConfirmed;

    public ChangePasswordRequest()
    {

    }

    public ChangePasswordRequest(String oldPassword,String newPassword,String newPasswordConfirmed)
    {
        this.oldPassword=oldPassword;
        this.newPassword=newPassword;
        this.newPasswordConfirmed=newPasswordConfirmed;
    }

    public String getOldPassword() {
        return oldPassword;
    }
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
    public String getNewPassword() {
        return newPassword;
    }
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    public String getNewPasswordConfirmed() {
        return newPasswordConfirmed;
    }
    public void setNewPasswordConfirmed(String newPasswordConfirmed) {
        this.newPasswordConfirmed = newPasswordConfirmed;
    }

    
}
