package turbogram.Models;

public class UpdateModel {
    private String changeDate;
    private Long id;
    private boolean isNew;
    private String newValue;
    private int type;
    private int userId;

    public UpdateModel(Long var1, int var2, String var4, int var5, boolean var6, String var7) {
        this.id = var1;
        this.type = var2;
        this.newValue = var4;
        this.userId = var5;
        this.isNew = var6;
        this.changeDate = var7;
    }

    public String getChangeDate() {
        return this.changeDate;
    }

    public String getNewValue() {
        return this.newValue;
    }

    public int getType() {
        return this.type;
    }

    public int getUserId() {
        return this.userId;
    }

    public boolean isNew() {
        return this.isNew;
    }

    public void setChangeDate(String var1) {
        this.changeDate = var1;
    }

    public void setNew(boolean var1) {
        this.isNew = var1;
    }

    public void setNewValue(String var1) {
        this.newValue = var1;
    }

    public void setType(int var1) {
        this.type = var1;
    }

    public void setUserId(int var1) {
        this.userId = var1;
    }
}
