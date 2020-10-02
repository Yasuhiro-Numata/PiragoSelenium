package model;

public class TestDataModel {
    private Account account;
    private SearchConditions searchConditions;

    public SearchConditions getSearchConditions() {
        return searchConditions;
    }

    public void setSearchConditions(SearchConditions searchConditions) {
        this.searchConditions = searchConditions;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
