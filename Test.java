import java.util.*;
import java.io.*;
class user{
    String Name,Password;
    char role;
    public void showmenu(HashtableMemberList list){
        while (true) {
            if (this.role!='A'&&this.role!='O'){
                System.out.println("----------欢迎进入访客菜单----------");
                System.out.print("请选择你要进行的操作：\n1.下载文件\n2.修改密码\n3.展示信息\n4.退出\nPlease input the number:");
                int i;
                Scanner scanner = new Scanner(System.in);
                try{
                    i = scanner.nextInt();
                }
                catch(Exception e){
                    System.out.println("输入错误，请重新输入！");
                    continue;
                }
                if (i==1){
                    System.out.println("文件下载成功！");
                }
                else if (i==2){
                    System.out.print("请输入新密码：");
                    String newpassword = scanner.next();
                    this.Password = newpassword;
                    System.out.println("密码修改成功！");
                }
                else if (i==3){
                    this.show();
                }
                else if (i==4){
                    System.out.println("退出成功！");
                    break;
                }
                else{
                    System.out.println("输入错误，请重新输入！");
                }
            }
            else if (this.role=='A'){
                System.out.println("----------欢迎进入成员菜单----------");
                System.out.print("请选择你要进行的操作：\n1.上传文件\n2.下载文件\n3.修改密码\n4.展示信息\n5.退出\nPlease input the number:");
                int i;
                Scanner scanner = new Scanner(System.in);
                try{
                i = scanner.nextInt();
                }
                catch(Exception e){
                    System.out.println("输入错误，请重新输入！");
                    continue;
                }
                if (i==1){
                    System.out.println("文件上传成功！");
                }
                else if (i==2){
                    System.out.println("文件下载成功！");
                }
                else if (i==3){
                    System.out.print("请输入新密码：");
                    String newpassword = scanner.next();
                    this.Password = newpassword;
                    System.out.println("密码修改成功！");
                }
                else if (i==4){
                    this.show();
                }
                else if (i==5){
                    System.out.println("退出成功！");
                    break;
                }
                else{
                    System.out.println("输入错误，请重新输入！");
                }                 
            }
            else if (this.role=='O'){
                System.out.println("----------欢迎进入操作员菜单----------");
                System.out.print("请选择你要进行的操作：\n1.修改用户\n2.删除用户\n3.新增用户\n4.列出用户\n5.下载文件\n6.展示信息\n7.修改密码\n8.退出\nPlease input the number:");
                int i;
                Scanner scanner = new Scanner(System.in);
                try{
                    i = scanner.nextInt();
                }
                catch(Exception e){
                    System.out.println("输入错误，请重新输入！");
                    continue;
                }
                if (i==8){
                    System.out.println("退出成功！");
                    break;
                }
                else if (i==1){//修改用户
                    String name;
                    System.out.print("请输入你要修改的用户的名称：");
                    name = scanner.next();
                    if (list.users.containsKey(name)){
                        user tmp = list.users.get(name);
                        System.out.print("请输入该用户的新密码：");
                        String newpassword = scanner.next();
                        tmp.changePassword(newpassword);
                        System.out.println("用户密码修改成功！");
                        System.out.print("请输入该用户的权限（A/O/B）：");
                        char role;
                        try{
                            role = scanner.next().charAt(0);
                        }
                        catch(Exception e){
                            System.out.println("输入错误，用户权限修改失败！");
                            continue;
                        }
                        tmp.changeRole(role);
                        System.out.println("用户权限修改成功！");
                    }
                    else{
                        System.out.println("用户不存在，修改失败！");
                    }
                }
                else if (i==2){//删除用户
                    String name;
                    System.out.print("请输入你要删除的用户的名称：");
                    name = scanner.next();
                    if (list.users.containsKey(name)){
                        list.users.remove(name);
                        System.out.println("用户删除成功！");
                    }
                    else{
                        System.out.println("用户不存在，删除失败！");
                    }
                }
                else if (i==3){//新增用户
                    String name,password;
                    char role;
                    System.out.print("请输入新用户的名称：");
                    name = scanner.next();
                    System.out.print("请输入新用户的密码：");
                    password = scanner.next();
                    System.out.print("请输入新用户的权限（A/O/B）：");
                    try{
                        role = scanner.next().charAt(0);
                    }
                    catch(Exception e){
                        System.out.println("输入错误，用户新增失败！");
                        continue;
                    }
                    list.add(name,password,role);
                    System.out.println("用户新增成功！");
                }
                else if (i==4){//列出用户
                    list.MemberList();
                }
                else if (i==5){//下载文件
                    System.out.println("文件下载成功！");
                }
                else if (i==6){//展示信息
                    this.show();
                }
                else if (i==7){//修改密码
                    System.out.print("请输入新密码：");
                    String newpassword = scanner.next();
                    this.Password = newpassword;
                    System.out.println("密码修改成功！");
                }
            }
        }
    }
    public void show(){
        System.out.println("----------用户信息----------");
        System.out.println("姓名："+Name);
        System.out.println("密码："+Password);
        if (role=='A')
            System.out.println("Administrator");
        else if (role=='O')
            System.out.println("Operator");
        else 
            System.out.println("Browser");
    }
    public void setter(String A,String B,char c){
        this.Name=A;
        this.Password=B;
        this.role=c;
    }
    public void changePassword(String A){
        this.Password=A;
    }
    public void changeRole(char c){
        this.role=c;
    }
    public String getPassword(){
        return this.Password;
    }
    public char getRole(){
        return this.role;
    }
    user(String A,String B,char c){
        this.Name=A;
        this.Password=B;
        this.role=c;
    }
    user(user c){
        this.Name=c.Name;
        this.Password=c.Password;
        this.role=c.role;
    }
}
class HashtableMemberList {
    Hashtable<String, user> users = new Hashtable<String, user>();
    HashtableMemberList(){
        users.put("whut001", new user("whut001","whut001",'A'));
        users.put("whut002", new user("whut002","whut002",'O'));
        users.put("whut003", new user("whut003","whut003",'B'));
    }
    public void MemberList(){
        System.out.println("---------------用户列表---------------");
        Enumeration<String> keys = this.users.keys();
        do {
            String key = keys.nextElement();
            user tmp = users.get(key);
            String Password = tmp.getPassword();
            System.out.print("Name: " + key +" Password: " + Password+" Role: ");
            System.out.println(tmp.getRole());
        }while (keys.hasMoreElements());
    }
    public int MemberListSize(){
        return this.users.size();
    }
    public void add(String Name,String Password,char role){
        users.put(Name,new user(Name,Password,role));
    }
}
public class Test {
    public static void main(String[] args) {
        HashtableMemberList list = new HashtableMemberList();
        int j = list.MemberListSize();
        int i;
        while(true){
            System.out.println("----------欢迎进入档案系统----------");
            System.out.print("请选择你要进行的操作：\n1.登录\n2.退出\nPlease input the number:");
            Scanner scanner = new Scanner(System.in);
            try{
                i = scanner.nextInt();
            }
            catch(Exception e){
                System.out.println("输入错误，请重新输入！");
                continue;
            }
            if (i==1){
                System.out.print("请输入用户名：");
                String username = scanner.next();
                if (list.users.containsKey(username)){
                    user tmp = list.users.get(username);
                    System.out.print("请输入密码：");
                    String password = scanner.next();
                    if (password.equals(tmp.getPassword())){
                        System.out.println("登录成功！");
                        tmp.showmenu(list);
                    }
                    else{
                        System.out.println("密码错误，请重新输入！");
                    }
                }
                else{
                    System.out.println("用户名不存在，请重新输入！");
                }
            }
            else if (i==2){
                System.out.println("退出成功！");
                scanner.close();
                break;
            }
            else{
                System.out.println("输入错误，请重新输入！");
            }
        }
    }
}
