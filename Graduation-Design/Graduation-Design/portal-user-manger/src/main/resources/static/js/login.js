
$(function() {
    
	//聚焦
	 $("body div.userBox").mousemove(function(){
	 	// console.log("聚焦到我了");
	 	$("body div#color").css("background-color","#00000069");//#333/#00000069
	 });
	  $("body div.userBox").mouseleave(function(){
	 	// console.log("聚焦到我了");
	 	$("body div#color").css("background-color","");
     });
     var systemSelect = function(){
        var userType = localStorage.getItem('userType');
        if(userType==1){
            $('#systemTitle').text("学生入口系统")
        }else if(userType==2){
            $('#systemTitle').text("教师入口系统")
        }else if(userType==3){
            $('#systemTitle').text("管理入口系统")
        }
    }
    systemSelect();

	//选择登录或者注册函数
    select();
    //注册函数
    register();
    //登录函数
    login();
    // 提示框消失
    tip($("#rgAccount"));
    tip($("#rgPassword2"));
    tip($("#rgPassword1"));
    tip($("#account"));
    tip($("password"));
    // rg_ajax();
});
// 选择登陆或者注册
var select = function() {
    for (var i = 0; i < 2; i++) {
        $("div.userBox ul.select li").eq(i).click(function() {
            $("div.userBox ul.select li").removeClass("active")
            $(this).addClass("active");
            // console.log($(this).index());
            if ($(this).index() == 0) {
                $('div.userBox div#loginBox').removeClass('hide');
                $('div.userBox div#registerBox').addClass('hide');
            }
            if ($(this).index() == 1) {
                $('div.userBox div#registerBox').removeClass('hide');
                $('div.userBox div#loginBox').addClass('hide');
            }
        });
    }
}

//注册接口
var rg_ajax = function(username,password,userType) {
    var da = {'username':username,
         'password':password};
    $.ajax({
        type: "POST",
        dataType:"json",
        //contentType: "application/json;charset=UTF-8",
        url: commonIp+"common/register",
        //data: 'username='+username+
        //'&password='+password,
        data:{
            'user_name':username,
            'user_password':password,
            'user_type':userType
        },
        //data: JSON.stringify(da),
        success: function(xhr) {
            // var obj = JSON.stringify(xhr);
            var obj = xhr;
            console.log(obj);
            /*
            if(obj=="username_exist"){
                // console.log("用户已存在");
                $("div#tip span").show();
                $("div#tip span").html("用户已存在！");
            }*/
            if(obj=="注册成功"){
                // console.log("注册成功");
                $("div#tip span").show();
                $("div#tip span").html("注册成功！");
                userHref(userType);
            }
            else{
                // console.log("注册失败！");
                $("div#tip span").show();
                $("div#tip span").html("注册失败！");
            }
        },
        error: function(xhr, type) {
            console.log(2);
            console.log(xhr);
            console.log(1);
            console.log(type);
        }
    });
}

//注册
var register = function() {
    var username = $("#rgAccount");
    var password1 = $("#rgPassword1");
    var password2 = $("#rgPassword2");
    var registerBn = $("#registerBn");
    var reg=/^[1-9]\d*$|^0$/;//正则判断
    registerBn.click(function() {
        if (username.val().split('').length > 10||reg.test(username.val()) != true) {
            // console.log("账号错误");
            $("div#tip span").show();
            $("div#tip span").html("账号错误！");
        }
        else if  (password1.val().split('').length < 8||password1.val().split('').length > 16) {
            console.log("密码错误");
            $("div#tip span").show();
            $("div#tip span").html("密码错误！");
        }
        else if (password2.val() != password1.val()) {
            // console.log("密码不一致");
            $("div#tip span").show();
            $("div#tip span").html("密码不一致！");
        }
        else if (username.val().split('').length <=10 && reg.test(username.val()) == true&&password1.val().split('').length >= 8
        	||password1.val().split('').length <=16&&password2.val() == password1.val()) {
            // console.log("提交成功");
            var userType = localStorage.getItem('userType');
        	rg_ajax(username.val(),password2.val(),userType);
        }else{
            // console("账号错误!");
            $("div#tip span").show();
            $("div#tip span").html("账号错误！");
        }
    });
}

// 登录接口
var lg_ajax = function(username,password,userType) {
    $.ajax({
        type: "POST",
        dataType:"json",
        url: commonIp+"common/login",
        /*
        data: 
        'user_name='+username+
        '&user_password='+password+
        '&user_type='+userType,
        */
       data:{
        'user_name':username,
        'user_password':password,
        'user_type':userType
    },
        success: function(xhr) {
            console.log(xhr);
            if(xhr==2){
                // console.log("登陆失败");
                $("div#tip span").show();
                $("div#tip span").html("密码错误");
            }
            else if(xhr==1){
                // console.log("登陆成功");
                // $("div#tip span").show();
                // $("div#tip span").html("登陆成功");
                userHref(userType);
            }
            else{
                // console.log("登陆失败！");
                $("div#tip span").show();
                $("div#tip span").html("账号不存在");
            }
        },
         error: function(xhr, type) {
            console.log(2);
            console.log(xhr);
            console.log(1);
            console.log(type);
        }
    });
}
// lg_ajax();
// 登录
var login = function() {
    var username = $("#account");
    var password = $("#password");
    var loginBn = $("#loginBn");
    var reg=/^[1-9]\d*$|^0$/;//正则判断
        loginBn.click(function() {
        // window.location.href = "index.html";
        /*
        var userType = localStorage.getItem("userType");
            if(userType==1){
                window.location.href = "student/content.html";
            }else if(userType==2){
                window.location.href = "teacher/content.html";
            }else if(userType==3){
                window.location.href = "manager/content.html";
            }
            */
        
            if (username.val().split('').length > 10||reg.test(username.val()) != true) {
                // console.log("账号错误");
                $("div#tip span").show();
                $("div#tip span").html("账号错误！");
            }
            else if (password.val().split('').length < 8||password.val().split('').length > 16) {
                // console.log("密码错误");
                $("div#tip span").show();
                $("div#tip span").html("密码错误！");
            }
            else if (username.val().split('').length <=10 && reg.test(username.val()) == true&&password.val().split('').length >= 8
                ||password.val().split('').length <=16) {
                // console.log("提交成功");
                var userType = localStorage.getItem('userType');
                lg_ajax(username.val(),password.val(),userType);
            }
            else{
                // console.log("账号错误!");
                // $("div#tip span").show();
                // $("div#tip span").html("账号错误!");
                console.log("操作错误");
            }
        });
}
var tip = function(a){
    a.click(function(){
        $("div#tip span").hide();
    });
}

var userHref = function(userType){
    
    if(userType==1){
        window.location.href = "student/content.html";
    }else if(userType==2){
        window.location.href = "teacher/content.html";
    }else if(userType==3){
        window.location.href = "manager/content.html";
    }

}
