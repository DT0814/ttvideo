$(function () {
    $('#codeImage').attr("src", "common/getCode?timestamp=" + (new Date()).valueOf());
    $('#codeImage1').attr("src", "common/getCode?timestamp=" + (new Date()).valueOf());
    $(".content .con_right .left").click(function (e) {
        $(this).css({"color": "#333333", "border-bottom": "2px solid #2e558e"});
        $(".content .con_right .right").css({"color": "#999999", "border-bottom": "2px solid #dedede"});
        $(".content .con_right ul .con_r_left").css("display", "block");
        $(".content .con_right ul .con_r_right").css("display", "none");
    });
    $(".content .con_right .right").click(function (e) {
        $(this).css({"color": "#333333", "border-bottom": "2px solid #2e558e"});
        $(".content .con_right .left").css({"color": "#999999", "border-bottom": "2px solid #dedede"});
        $(".content .con_right ul .con_r_right").css("display", "block");
        $(".content .con_right ul .con_r_left").css("display", "none");
    });

    $('#btn_Register').click(function () {
        if ($.trim($('#registerAccount').val()) == '') {
            alert('请输入您的用户名');
            return false;
        } else if ($.trim($('#registerPass').val()) != $.trim($('#registerRePass').val())) {
            alert('两次输入密码不一致');
            return false;
        } else {
            axios.post('/user/register', $("#registerForm").serialize())
                .then(function (response) {
                    console.log(response);
                    var result = response.data;
                    if (result.code != 200) {
                        alert(result.msg);
                    } else {
                        logionCookie(result.data.name, result.data.uid, result.data.isvip);
                        alert("注册成功点击确定去首页");
                        location.href = "/index.html";
                    }
                });
            return true;
        }
    });

    $('#btn_Login').click(function () {
        if ($.trim($('#loginAccount').val()) == '') {
            alert('请输入您的用户名');
            return false;
        } else if ($.trim($('#loginPass').val()) == '') {
            alert('请输入密码');
            return false;
        } else {
            axios.post('/user/login', $("#loginForm").serialize())
                .then(function (response) {
                    console.log(response);
                    var result = response.data;
                    if (result.code != 200) {
                        alert(result.msg);
                    } else {
                        logionCookie(result.data.name, result.data.uid, result.data.isvip);
                        alert("登录成功,点击确定返回首页");
                        location.href = "/index.html";
                    }
                });
            return true;
        }
    });

    $("#flushLoginValiCode1,#flushLoginValiCode2").click(function () {
        $("#loginImgCode").attr("src", "/handler/GetLoginCode.ashx?" + Math.random());
    });
    // 更换验证码
    $('#codeImage').click(function () {
        $('#codeImage').attr("src", "common/getCode?timestamp=" + (new Date()).valueOf());
    });
    $('#codeImage1').click(function () {
        $('#codeImage1').attr("src", "common/getCode?timestamp=" + (new Date()).valueOf());
    });
    $("#backIndex").click(function () {
        location.href = "/index.html"
    })
});

function logionCookie(name, uid, svip) {
    $.cookie('user_name', name, {expires: 1, path: '/'});
    $.cookie('user_uid', uid, {expires: 1, path: '/'});
    $.cookie('user_svip', svip, {expires: 1, path: '/'});
}