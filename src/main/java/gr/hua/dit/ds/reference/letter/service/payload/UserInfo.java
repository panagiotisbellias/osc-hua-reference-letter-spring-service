package gr.hua.dit.ds.reference.letter.service.payload;

public class UserInfo {
    
    private String username, fullName, email, type;

    public UserInfo(String username, String fullName, String email, String type){
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.type = type;
    }

    public UserInfo(){
        this.username = "username";
        this.fullName = "fullName";
        this.email = "email";
        this.type = "type";
    }

    public String getUsername(){
        return this.username;
    }

    public String getFullName(){
        return this.fullName;
    }

    public String getEmail(){
        return this.email;
    }

    public String getType(){
        return this.type;
    }

}
