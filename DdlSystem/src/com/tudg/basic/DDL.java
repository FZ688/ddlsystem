package com.tudg.basic;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;



public class DDL {
    //存储用户和管理员数据的数组
    ArrayList<User> userList =new ArrayList<>();
    ArrayList<Admin> adminList = new ArrayList<>();

    private Admin loginAdmin;
    private User loginUser;


    public  void startMenu(){
        System.out.println("======欢迎使用避免作业每次ddl系统=======");
        System.out.println("            1.用户");
        System.out.println("            2.管理员");
        System.out.println("            3.退出");
        System.out.println("=====================================");
        System.out.println("请选择:");
        Scanner sc=new Scanner(System.in);
        int command =sc.nextInt();
        switch (command){
            case 1:
                UserLoginFrame();
                break;
            case 2:
                AdminLoginFrame();
                break;
            case 3:
                System.out.println("感谢您的使用！");
                System.exit(0);
            default:
                System.out.println("警告：您输入的指令不存在，请重新输入");
        }
    }
    private void UserLoginFrame(){
        User user =null;

        while (true) {
            System.out.println("--------用户登录----------");
            System.out.println("-----1.已有账号,请登录------");
            System.out.println("-----2.没有账号，请注册------");
            System.out.println("请选择:");
            user = null;
            Scanner sc =new Scanner(System.in);
            String selcet = sc.next();
            switch(selcet){
                case "1":
                    user=userLogin();
                    loginUser=user;
                    break;
                case "2":
                    user = createUser();
                    loginUser =user;

                    userList.add(user);
                    break;
            }
            if(user!=null){
                System.out.println("登录成功！");
                System.out.println("欢迎用户"+user.getId()+"登录本系统，记得完成作业哦~~");
                userMainFrame();

            }else{
                System.out.println("账号或密码有误");
                System.out.println(" 操作选项：【1】重新尝试，请输入1 \t【2】结束运行，请输入Enter键");
                System.out.println("请选择:");
                String command= sc.next();
                if(!command.equals("1")){
                    System.exit(0);
                }
            }
        }
    }

    private void userMainFrame() {
        while (true) {
            System.out.print("——————>用户：\n\n");
            System.out.println("\t\t\t1.创建作业\n");
            System.out.println("\t\t\t2.编辑作业\n");
            System.out.println("\t\t\t3.搜索作业\n");
            System.out.println("\t\t\t4.删除作业\n");
            System.out.println("\t\t\t5.返回主菜单\n");
            System.out.println("\t\t\t6.设置个人信息\n");
            System.out.println("**********************************");
            System.out.println("请选择:");
            Scanner sc1 =new Scanner(System.in);
            String command = sc1.next();

            switch (command){
                case "1":
                    addHomeworkAtUser();

                    break;
                case "2":
                    editHomeworkAtUser();

                    break;
                case "3":
                    queryHomework();
                    break;
                case "4":
                    deleteHomework();
                    break;
                case "5":
                    startMenu();
                    break;
                case "6":
                    editUser();;
                    break;
                default:
                    System.out.println("您输入的指令有误，请重新输入~~");
            }
        }
    }
    //删除作业
    private void deleteHomework() {
        System.out.println("-----删除指定作业------");
        Scanner sc =new Scanner(System.in);
        System.out.println("请输入用户id:");
        String userId = sc.next();
        User user =null;

        loginUser=getUserByUserId(userId);
        System.out.println("请输入作业id:");
        String searchId =sc.next();
        Homework homework =userGetHomeworkByHomeworkId(searchId);
        if (homework==null){
            System.out.println("查无此作业~");
        }else {
            System.out.println("查询成功~");
            System.out.println("请问您确定要删除作业吗?  y/n");

            String command = sc.next();
            switch (command) {
                case "y":
                    System.out.println("开始删除....");
                    int index =loginUser.getHomeworks().indexOf(homework);
                    loginUser.getHomeworks().remove(index);
                    System.out.println("删除成功~");

                default:
                    System.out.println("好的，作业保留~");
            }
        }
    }

    //用户创建作业
    private void addHomeworkAtUser(){

        Scanner sc =new Scanner(System.in);
        System.out.println("------创建作业-------");
        Homework newHomeWork =new Homework() ;
        System.out.println("请输入用户id：");
        String userId = sc.next();
        User user =null;
        user =getUserByUserId(userId);
        if(user==null){
            System.out.println("您输入的id不存在，请重新确认~");
        }else {
            System.out.println("查询成功~");
            loginUser = getUserByUserId(userId);


            System.out.println("请输入作业标题:");
            String HomeworkHeadLine = sc.next();
            newHomeWork.setHeadline(HomeworkHeadLine);
            System.out.println("请输入作业内容:");
            String HomeworkContext = sc.next();
            newHomeWork.setContext(HomeworkContext);
            System.out.println("请输入作业截止日期:");
            String HomeworkDeadline = sc.next();
            newHomeWork.setDeadline(HomeworkDeadline);
            System.out.println("请设置作业优先级(高、中、低):");
            String priority = sc.next();
            newHomeWork.setPriority(priority);
            String newHomeWorkId = createHomeworkId();
            newHomeWork.setHomeworkId(newHomeWorkId);
            loginUser.getHomeworks().add(newHomeWork);
            System.out.println("恭喜您，添加成功，生成的作业id是：" + newHomeWork.getHomeworkId());
        }

    }
    //编辑作业
    private void editHomeworkAtUser(){
        System.out.println("--------编辑作业------");
        System.out.println("正在进行作业编辑修改......");
        System.out.println("请输入用户id：");
        Scanner sc2=new Scanner(System.in);
        String userId = sc2.next();
        User user =null;
        user =getUserByUserId(userId);
        if(user==null){
            System.out.println("您输入的id不存在，请重新确认~");
        }else {
            System.out.println("查询成功~");
            loginUser = getUserByUserId(userId);

            System.out.println("请输入作业id");
            Scanner sc1 = new Scanner(System.in);
            String homeworkId = sc1.next();
            System.out.println("正在查询.....");
            Homework homework = null;
            homework = userGetHomeworkByHomeworkId(homeworkId);
            if (homework == null) {
                System.out.println("您输入的作业id不存在，请再次确认~");
            } else {
                while (true) {

                    System.out.println("编辑提示： 【1】作业标题");
                    System.out.println("         【2】作业内容");
                    System.out.println("         【3】作业截止日期");
                    System.out.println("         【4】作业优先级");
                    System.out.println("请选择:");
                    Scanner sc = new Scanner(System.in);
                    int select = sc.nextInt();
                    switch (select) {
                        case 1:
                            System.out.println("替换作业标题为:");
                            homework.setHeadline(sc.next());
                            break;
                        case 2:
                            System.out.println("替换作业内容为:");
                            homework.setContext(sc.next());
                            break;
                        case 3:
                            System.out.println("替换截止日期为(yyyy-MM-dd):");
                            homework.setDeadline(sc.next());
                            break;
                        case 4:
                            System.out.println("更改作业优先级为:");
                            homework.setPriority(sc.next());
                            break;
                    }
                    System.out.println("是否继续修改：【1】继续，请输入1   【2】返回，请按任意其他键");
                    System.out.println("请选择:");
                    String isContinue = sc.next();
                    if (!isContinue.equals("1")) {
                        break;
                    }

                }
                int index = loginUser.getHomeworks().indexOf(userGetHomeworkByHomeworkId(homeworkId));
                loginUser.getHomeworks().set(index, homework);
            }
        }
    }

    //搜索作业
    private void queryHomework(){
        System.out.println("----正在查询中-------");
        Scanner sc =new Scanner(System.in);
        System.out.println("请输入用户id：");
        String userId = sc.next();
        User user =null;
        user =getUserByUserId(userId);
        if(user==null){
            System.out.println("您输入的id不存在，请重新确认~");
        }else {
            System.out.println("查询成功~");
            loginUser = getUserByUserId(userId);

            System.out.println("请输入作业id:");
            String homeworkId = sc.next();
            Homework homework = userGetHomeworkByHomeworkId(homeworkId);
            if (homework == null) {
                System.out.println("查无此作业");
            } else {
                System.out.println("截止日期:" + homework.getDeadline());
                System.out.println("优先级:" + homework.getPriority());
                System.out.println("作业标题:" + homework.getHeadline());
                System.out.println("作业内容:" + homework.getContext());
                System.out.println("请尽快完成作业哦~~~");
                System.out.println("------------------------");
            }
        }
    }
    private Admin adminLogin() {
            System.out.println("==管理员登录==");
            if (adminList.size() == 0) {
                System.out.println("当前系统中无任何管理员账户");
                return null;
            }
            while (true) {
                System.out.println("请输入您的管理员id:");
                Scanner sc = new Scanner(System.in);
                String adminId = sc.next();
                Admin admin = getAdminByAdiminId(adminId);
                if (admin == null) {
                    System.out.println("您输入的管理员id不存在，请再次确认~~");

                } else {
                    while (true) {
                        System.out.println("请输入您的登录密码:");
                        String password = sc.next();
                        if (admin.getAdminPassWord().equals(password)) {
                            loginAdmin = admin;
                            return loginAdmin;
                        } else {
                            System.out.println("您输入的密码不正确，请重新确认~~");

                        }

                    }
                }
            }
        }

    //用户登录操作
    private User userLogin(){
        System.out.println("==用户登录==");
        if(userList.size()==0){
            System.out.println("当前系统中无任何user账户");
            return null;
        }
        while (true) {
            Scanner sc =new Scanner(System.in);
            System.out.println("请输入您的用户id:");
            String userId=sc.next();
            User user =getUserByUserId(userId);
            if(user==null){
                System.out.println("您输入的用户id不存在，请再次确认~~");

            }else{
                while (true) {
                    System.out.println("请输入您的登录密码:");
                    String password =sc.next();
                    if(user.getUserPassWord().equals(password)){
                        loginUser=user;
                        return loginUser;
                    }else {
                        System.out.println("您输入的密码不正确，请重新确认~~");
                    }
                }
            }
        }
    }
    //管理员登录页面
    //return 返回账号和密码
    private  void AdminLoginFrame(){
        Admin admin =null;
        while (true) {
            System.out.println("********************管理员登录********************");
            System.out.println("****************1.已有账号,请登录****************" );
            System.out.println("****************2.没有账号,请注册****************");
            System.out.println("请选择:");
            admin =null;
            Scanner sc =new Scanner(System.in);
            String selcet = sc.next();
            switch(selcet){
                case "1":
                    admin = adminLogin();

                    break;
                case "2":
                    admin = createAdmin();
                    adminList.add(admin);
                    break;
            }
            if(admin!=null){
                System.out.println("登录成功!");
                System.out.println("欢迎管理员"+admin.getAdminId()+"登录本系统~~");
                manage_MainFrame();
            }else {
                System.out.println("账号或密码有误");
                System.out.println(" 操作选项：【1】重新尝试，请输入1 \t【2】结束运行，请输入Enter键");
                System.out.println("请选择:");
                String command= sc.next();
                if(!command.equals("1")){
                    System.exit(0);
                }
            }
        }
    }
    //管理员界面
    public void manage_MainFrame(){
        while (true) {
            System.out.print("——————>管理员：\n\n");
            System.out.println("\t\t\t1.添加用户\n");
            System.out.println("\t\t\t2.编辑用户\n");
            System.out.println("\t\t\t3.删除用户\n");
            System.out.println("\t\t\t4.创建作业\n");
            System.out.println("\t\t\t5.编辑作业\n");
            System.out.println("\t\t\t6.搜索作业\n");
            System.out.println("\t\t\t7.删除作业\n");
            System.out.println("\t\t\t8.返回主菜单\n");
            System.out.println("**********************************");
            System.out.println("请选择:");
            Scanner sc =new Scanner(System.in);
            String select =sc.next();

            switch (select){
                case "1":
                    add_UserFrame(userList);
                    break;
                case "2":
                    manage_EditUser();
                    break;
                case "3":
                    deleteUser();
                    break;
                case "4" :
                    addHomeworkAtUser();
                    break;
                case "5":
                    editHomeworkAtUser();
                    break;
                case "6":
                    queryHomework();
                    break;
                case "7":
                    deleteHomework();
                    break;
                case "8":
                    startMenu();
                    break;
                default:
                    System.out.println("您输入的指令有误，请重试！");
            }
        }


    }
    //管理员删除用户
    private void deleteUser(){
        System.out.println("====正在进行删除用户操作=====");
        System.out.println("请输入您想要删除的用户id:");
        Scanner sc =new Scanner(System.in);
        String userId =sc.next();
        User user =null;
        user =getUserByUserId(userId);
        if(user ==null){
            System.out.println("您输入的id不存在，请重新确认");
        }else{
            System.out.println("查询成功~");
            System.out.println("请问您确定要删除作业吗?  y/n");

            String command = sc.next();
            switch (command) {
                case "y":
                    System.out.println("开始删除....");
                    int index =userList.indexOf(user);
                    System.out.println("删除成功~");
                    break;
                default:
                    System.out.println("好的，作业保留~");

            }
        }
    }
    //管理员编辑用户
    private void manage_EditUser(){
        System.out.println("-----编辑用户信息------");
        System.out.println("请输入您的要编辑的用户的id:");
        Scanner sc =new Scanner(System.in);
        String userId=sc.next();
        User user =null;
        user =getUserByUserId(userId);
        if(user==null){
            System.out.println("您输入的id错误，请重新输入~");
        }else {
            loginUser =user;
            while (true){
                System.out.println("修改提示：     【1】个人昵称");
                System.out.println("             【2】账户密码");
                Scanner sc1 =new Scanner(System.in);
                int select =sc1.nextInt();
                switch (select){
                    case 1:
                        System.out.println("更改昵称为：");
                        user.setName(sc.next());
                        System.out.println("修改成功~");
                        break;
                    case 2:
                        manage_UpadatePassWord(user);
                        break;
                }
                System.out.println("是否继续修改：【1】继续，请输入1   【2】返回，请按任意其他键");
                System.out.println("请选择:");
                String isContinue =sc.next();
                if(!isContinue.equals("1")){
                    break;
                }
            }

        }
    }
    //管理员修改用户密码
    private void manage_UpadatePassWord(User user){

            System.out.println("请输入用户的密码:");
            Scanner sc =new Scanner(System.in);
            String passWord =sc.next();
            if(user.getUserPassWord().equals(passWord)){
                while(true){
                    System.out.println("请您输入新密码:");
                    String newPassWord = sc.next();
                    System.out.println("请您再次输入新密码:");
                    String okPassWord = sc.next();
                    if(okPassWord.equals(newPassWord)){
                        loginUser.setUserPassWord(okPassWord);
                        System.out.println("密码修改成功！");
                        return;
                    }else {
                        System.out.println("您输入的两次密码不一致~~");
                    }
                }
            }

    }
    //创建管理员id
    private String createAdminId() {


        while (true) {
            System.out.println("请输入您要注册的管理员id:");
            Scanner sc =new Scanner(System.in);
            String adminid = sc.next();
            Admin admin = getAdminByAdiminId(adminid);
            if (admin == null) {
                return adminid;
            }
        }
    }
    //创建用户id
    private String createUserId(){
        while (true) {
            System.out.println("请输入您要注册的id:");
            Scanner sc =new Scanner(System.in);
            String userid =sc.next();
            User user =getUserByUserId(userid);
            if(user==null){
                return userid;
            }
        }
    }

    //管理员账号可自行添加
    private Admin createAdmin(){
        System.out.println("=====创建管理员账号=====");
        Admin admin =new Admin();

        admin.setAdminId(createAdminId());
        while (true) {
            System.out.println("请输入您的账户密码:");
            Scanner sc =new Scanner(System.in);
            String password =sc.next();
            System.out.println("请再次确认您的账户密码:");
            String okPassWord= sc.next();
            if(okPassWord.equals(password)){
                admin.setAdminPassWord(okPassWord);
                return admin;
            }else{
                System.out.println("您输入的两次密码不一致，请重试~~");
            }
        }
    }
    //用户注册
    private User createUser(){
        System.out.println("------用户注册------");
        User user =new User();
        user.setId(createUserId());
        System.out.println("请输入您的昵称:");
        Scanner sc1 =new Scanner(System.in);
        user.setName(sc1.next());
        while (true) {
            Scanner sc =new Scanner(System.in);
            System.out.println("请输入您的账户密码:");
            String  password = sc.next();
            System.out.println("请再次输入您的账户密码:");
            String okPassWord =sc.next();
            if(okPassWord.equals(password)){
                user.setUserPassWord(okPassWord);
                return user;
            }else{
                System.out.println("您两次输入的密码不一致，请重试~~");
            }
        }
    }
    //管理员添加用户,且默认用户密码为用户的初始id
    private void add_UserFrame(ArrayList<User> users){
        Manage manage =new Manage();
        System.out.println("请选择你要添加的用户个数:");
        Scanner sc1 =new Scanner(System.in);
        int count = sc1.nextInt();
        int i;
        for (i = 1; i <= count; i++) {
            System.out.println("请输入第"+i+"个用户的id：");
            Scanner sc =new Scanner(System.in);
            String userId =sc.next();
            System.out.println("请输入第"+i+"个用户的名字：");
            String name =sc.next();
            if(manage.add_user(userId,userId,name,users)){
                System.out.println("第"+i+"个学生添加成功~");
            }else {
                System.out.println("从第"+i+"个学生起添加失败！");
                break;
            }
            if (i!=count+1){
                System.out.println("是否继续添加<Y/N>");
                if(sc.next().equals("Y")){
                    add_UserFrame(users);
                }
            }
            
        }
    }
    //用户设置自己的个人信息
    private void editUser(){
        System.out.println("-----修改个人信息------");
        System.out.println("请输入你的用户id:");
        Scanner sc =new Scanner(System.in);
        String userId=sc.next();
        User user =null;
        user =getUserByUserId(userId);
        if(user==null){
            System.out.println("您输入的id错误，请重新输入~");
        }else {
            while (true){
                System.out.println("修改提示：     【1】个人昵称");
                System.out.println("             【2】账户密码");
                Scanner sc1 =new Scanner(System.in);
                int select =sc1.nextInt();
                switch (select){
                    case 1:
                        System.out.println("更改昵称为：");
                        user.setName(sc.next());
                        System.out.println("修改成功~");
                        break;
                    case 2:
                        updatePassWord();
                        break;
                }
                System.out.println("是否继续修改：【1】继续，请输入1   【2】返回，请按任意其他键");
                System.out.println("请选择:");
                String isContinue =sc.next();
                if(!isContinue.equals("1")){
                    break;
                }
            }

        }
    }
    private  void updatePassWord(){

        while(true){
            System.out.println("请输入用户的密码:");
            Scanner sc =new Scanner(System.in);
            String passWord =sc.next();
            if(loginUser.getUserPassWord().equals(passWord)){
                while(true){
                    System.out.println("请您输入新密码:");
                    String newPassWord = sc.next();
                    System.out.println("请您再次输入新密码:");
                    String okPassWord = sc.next();
                    if(okPassWord.equals(newPassWord)){
                        loginUser.setUserPassWord(okPassWord);
                        System.out.println("密码修改成功！");
                        return;
                    }else {
                        System.out.println("您输入的两次密码不一致~~");
                    }
                }
            }
        }
    }


    //管理员去重
    private Admin getAdminByAdiminId(String adminid){
        for (int i=0;i<adminList.size();i++){
            Admin admin=adminList.get(i);
            if(admin.getAdminId().equals(adminid)){
                return admin;
            }
        }
        return null;
    }

    //用户名去重
    private User getUserByUserId(String userid){
        for (int i =0;i<userList.size();i++){
            User user =userList.get(i);
            if(user.getId().equals(userid)){
                return user;
            }
        }
        return null;
    }
    private String createHomeworkId(){
        while(true){
            String homewordid ="";
            Random r =new Random();
            for (int i = 0; i < 8; i++) {
                int data = r.nextInt(10);
                homewordid+=data;
            }
            Homework homework =userGetHomeworkByHomeworkId(homewordid);
            if(homework==null){
                return homewordid;
            }

        }
    }
    private Homework userGetHomeworkByHomeworkId(String homeworkId){
        for (int i = 0; i < loginUser.getHomeworks().size(); i++) {
            Homework homework =loginUser.getHomeworks().get(i);
            if(homework.getHomeworkId().equals(homeworkId)){
                return  homework;
            }

        }
        return null;
    }
}
