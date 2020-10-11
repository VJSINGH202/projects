$(document).ready(function () {
    var usernamelenght;
    var passwordlenght;
    var emaillenght;
    var biolenght;
    var doblenght;
    var categorylenght;
    var confirmpasslenght;
    $('[data-toggle="tooltip"]').tooltip();
    $('.alert-dismissible').fadeTo(5000, 500).slideUp(500, function () {
        $('.alert-dismissible').alert('close');
    });
    // Add comment
    $('#addcomment').submit(function (e) {
        e.preventDefault();
        var comment = $('#commentTxt').val();
        console.log(comment);
        if (comment.trim() === "" || comment.trim().length > 100 || comment.trim().length < 2)
        {
            $('#commentmsg').text("Enter atleat 2 character and atmost 100 character");
            $('#commentmsg').addClass("mt-1 text-danger");
        } else {
            $('#commentmsg').text("");
            $('#commentTxt').removeClass('mt-1 text-danger');
            var url = 'Comment';
            var vars = $(this).serialize();
            $.ajax({
                url: url,
                data: vars,
                type: 'POST',
                dataType: 'html',
                success: function (data, textStatus, jqXHR) {
                    $('#addcomment')[0].reset();
                    $('#cmsg').replaceWith($('#cmsg').html(data));
                    $('.alert-dismissible').fadeTo(5000, 500).slideUp(500, function () {
                        $('.alert-dismissible').alert('close');
                    });
                }
            });
        }

    });
    
    $('#addcategory').submit(function (e) {
        e.preventDefault();
        var category = $('#category-name').val().trim(' ');
        categorylenght = category.length;
        console.log(category.trim());
        if (!isValidAdminCategory(category,categorylenght)) {
            console.log("inside if");
            $('#category-msg').text("Enter atleast 2 characher and atmost 30 character");
            $('#category-msg').addClass('mt-1 text-danger');
        } else {
            console.log("inside else");
            $('#category-msg').text("");
            $('#category-msg').removeClass('mt-1 text-danger');
            var url = 'Admin';
            var vars = $(this).serialize();
            $.ajax({
                url: url,
                data: vars,
                type: 'POST',
                dataType: 'html',
                success: function (data, textStatus, jqXHR) {
                     $('#addcategory')[0].reset();
                    $('#msg:empty').css('display', 'block');
                    $('#addCategoryModal').modal('hide');
                    $('#msg').replaceWith($('#msg').html(data));
                    $('.alert-dismissible').fadeTo(5000, 500).slideUp(500, function () {
                        $('.alert-dismissible').alert('close');
                    });
                    $('#msg:empty').css('display', 'none');
                }
            });
        }
    });
    
    //  end of add comment
    /*$('#addcategory').submit(function (e) {
        e.preventDefault();
        var category = $('#category-name').val().trim(' ');
        console.log(category.trim());
        categorylenght = category.length;
        validateAddCategory(category, categorylenght, e);
    });
    */
    //start of login-form
    $('#login-form').submit(function (e){
         var username = $('#username-txt').val().trim(' ');
         var password = $('#password-txt').val().trim(' ');
         usernamelenght = username.length;
         passwordlenght = password.length;
        console.log(username);
        console.log(usernamelenght);
        console.log(password);
        validateLoginUsername(username,usernamelenght,e);
        validateLoginPassword(password,passwordlenght,e);
    });
    // end of login-form
    //start of feedback
    $('#feedback').submit(function (e){
         var feedback = $('#feedback-name').val();
        console.log(feedback.trim());
        if (feedback.trim() === "" || feedback.trim().lenght < 2 || feedback.trim().length > 100) {
            console.log("inside if");
            $('#feedback-msg').text("Enter atleast 2 characher and atmost 100 character");
            $('#feedback-msg').addClass('mt-1 text-danger');
            e.preventDefault();
        } else {
            console.log("inside else");
            //$('#admin-category').action="Admin";
            //$('#admin-category').submit();
            //$('#admin-category')[0].reset();
            $('#feedback-msg').text("");
            $('#feedback-msg').removeClass('mt-1 text-danger');
        }
    });
    // end of feedback
    //  end of add category
    $('#admin-category').submit(function (e){
        var category = $('#admin-category-name').val().trim(' ');
        console.log(category.trim());
        categorylenght = category.length;
        validateAdminCategory(category, categorylenght, e);
    });
    
    // start of addreport
    $('#addreport').submit(function (e) {
        e.preventDefault();
        var url = 'Admin';
        var vars = $(this).serialize();
        $.ajax({
            url: url,
            data: vars,
            type: 'POST',
            dataType: 'html',
            success: function (data, textStatus, jqXHR) {
                $('#amsg').replaceWith($('#amsg').html(data));
                $('.alert-dismissible').fadeTo(5000, 500).slideUp(500, function () {
                    $('.alert-dismissible').alert('close');
                });
            }
        });
    });
    //  end of addreport
    // start of addfavourite
    $('#addfavourite').submit(function (e) {
        e.preventDefault();
        var url = 'Admin';
        var vars = $(this).serialize();
        $.ajax({
            url: url,
            data: vars,
            type: 'POST',
            dataType: 'html',
            success: function (data, textStatus, jqXHR) {
                $('#amsg').replaceWith($('#amsg').html(data));
                var result = data.toString();
                if (result.search("Successfully Added to favourite") === 155)
                {
                    $('#btn-favourite').addClass('active');
                } else
                {
                    $('#btn-favourite').removeClass('active');
                }

                $('.alert-dismissible').fadeTo(5000, 500).slideUp(500, function () {
                    $('.alert-dismissible').alert('close');
                });
            }
        });
    });
    //  end of addfavourite
    // start of add like
    $('#addlike').submit(function (e) {
        e.preventDefault();
        var url = 'Admin';
        var vars = $(this).serialize();
        $.ajax({
            url: url,
            data: vars,
            type: 'POST',
            dataType: 'html',
            success: function (data, textStatus, jqXHR) {
                $('#amsg').replaceWith($('#amsg').html(data));
                var result = data.toString();
                if (result.search("Successfully like blog") === 155)
                {
                    $('#btn-like').addClass('active');
                } else
                {
                    $('#btn-like').removeClass('active');
                }
                $('.alert-dismissible').fadeTo(5000, 500).slideUp(500, function () {
                    $('.alert-dismissible').alert('close');
                });
            }
        });
    });
    //end of add like
    //<editor-fold defaultstate="collapsed" desc="comment">

    //</editor-fold>
    $('#signup-form').submit(function (e){
         var username = $('#reg-user').val().trim(' ');
         var password = $('#reg-pass').val().trim(' ');
         var email = $('#reg-email').val().trim(' ');
         var dob = $('#reg-dob').val().trim(' ');
         var bio = $('#reg-bio').val().trim(' ');
         usernamelenght = username.length;
         passwordlenght = password.length;
         emaillenght = email.length;
         biolenght = bio.length;
         doblenght = dob.length;
         console.log(username.trim()==="");
         validateUsername(username,usernamelenght,e);
         validatePassword(password,passwordlenght,e);
         validateEmail(email,emaillenght,e);
         validateDOB(dob,doblenght,e);
         validateBio(bio,biolenght,e);
     });
    $('#forgot-form').submit(function (e){
         var email = $('#forgot-email').val().trim(' ');
         emaillenght = email.length;
         console.log(email.trim()==="");
         validateForgotEmail(email,emaillenght,e);
     });
     
    $('#admin-add-user-form').submit(function (e){
         var username = $('#user-name').val().trim(' ');
         var password = $('#user-password').val().trim(' ');
         var email = $('#user-email').val().trim(' ');
         var dob = $('#user-dob').val().trim(' ');
         var bio = $('#user-bio').val().trim(' ');
         usernamelenght = username.length;
         passwordlenght = password.length;
         emaillenght = email.length;
         biolenght = bio.length;
         doblenght = dob.length;
         validateAdminAddUsername(username,usernamelenght,e);
         validateAdminAddPassword(password,passwordlenght,e);
         validateAdminAddEmail(email,emaillenght,e);
         validateAdminAddDob(dob,doblenght,e);
         validateAdminAddBio(bio,biolenght,e);
     });
     
    $('#update-password-form').submit(function (e){
         var password = $('#password').val().trim(' ');
         var confirmPassword = $('#confirm-password').val().trim(' ');
         passwordlenght = password.length;
         confirmpasslenght = confirmPassword.length;
         console.log(password.trim() === confirmPassword.trim());
         console.log($('#password').val());
         console.log(confirmPassword.trim());
         validateUpdatePassword(password,passwordlenght,e);
         validateUpdateConfirmPassword(confirmPassword,confirmpasslenght,e);
         validateCheckPassword(password,confirmPassword,e);
     });
    $('#blog-form').submit(function (e){
         var title = $('#blog-title').val().trim();
         var category = $('#blog-category').val().trim();
         var photo = $('#blog-photo').val().trim();
         var content = $('#blog-content').val().trim();
         console.log(typeof title);
         console.log(title.trim());
         console.log(title.length);
         console.log(category.trim());
         console.log(photo.trim());
         console.log(content.trim());
            validateBlogTitle(title,title.length,e);
            validateBlogCategory(category,e);
            validateBlogPhoto(photo,e);
            validateBlogContent(content,content.lenght,e);
            });
    $('#user-profile').submit(function (e){
        alert('oooooooo');
        var username = $('#profile-username').val().trim(' ');
        var email = $('#profile-email').val().trim(' ');
        var bio = $('#profile-bio').val().trim(' ');
        usernamelenght = username.length;
        emaillenght = email.length;
        biolenght = bio.length;
        validateProfileName(username,usernamelenght,e);
        validateProfileEmail(email,emaillenght,e);
        validateProfileBio(bio,biolenght,e);
        alert('out');
    });
});

function validateBlogTitle(title,titlelength,e){
    if(!isvalidTitle(title,titlelength)){
        $('#blog-title-msg').text("Please enter Blog title between (2 to 50) character.");
        $('#blog-title-msg').removeClass('mt-1 text-danger').addClass('mt-1 text-danger');
        e.preventDefault();
    }else{
        $('#blog-title-msg').text("");
        $('#blog-title-msg').removeClass('mt-1 text-danger');
    }
}

function validateBlogCategory(category,e){
    if(!isvalidCategory(category)){
        $('#blog-category-msg').text("Please choose a category...");
        $('#blog-category-msg').removeClass('mt-1 text-danger').addClass('mt-1 text-danger');
        e.preventDefault();
    }else{
        $('#blog-category-msg').text("");
        $('#blog-category-msg').removeClass('mt-1 text-danger');
    }
}

function validateBlogPhoto(photo,e){
     if(!isvalidPhoto(photo)){
        $('#blog-photo-msg').text("Please choose a photo...");
        $('#blog-photo-msg').removeClass('mt-1 text-danger').addClass('mt-1 text-danger');
        e.preventDefault();
    }else{
        $('#blog-photo-msg').text("");
        $('#blog-photo-msg').removeClass('mt-1 text-danger');
    }
}

function validateBlogContent(content,contentlength,e){
     if(!isvalidContent(content,contentlength)){
        $('#blog-content-msg').text("Please enter blog content between (2 to 10000) characters...");
        $('#blog-content-msg').removeClass('mt-1 text-danger').addClass('mt-1 text-danger');
        e.preventDefault();
    }else{
        $('#blog-content-msg').text("");
        $('#blog-content-msg').removeClass('mt-1 text-danger');
    }
}

function isvalidContent(content,contentlength){
    console.log(contentlength);
    return (!(content.trim() === ""));
}

function isvalidPhoto(photo){
    return !(photo.trim()==="");
}

function isvalidCategory(category){
    return category.trim()!=="none";
}

function isvalidTitle(title,titlelength){
    return ((!(title.trim() === "")) && (titlelength >=2) && (titlelength <= 50));
}

function validateAdminAddUsername(username,userlenght,e){
    if(!isvalidUser(username,userlenght)){
            $('#user-name-msg').text("Please enter your Username almost (20)character");
            $('#user-name-msg').removeClass('mt-1 text-danger').addClass('mt-1 text-danger');
            e.preventDefault();
    }else{
         $('#user-name-msg').text("");
          $('#user-name-msg').removeClass('mt-1 text-danger');
    }
}

function validateAdminAddPassword(password,passlenght,e){
    if(!isvalidPassword(password,passlenght)){
        $('#user-password-msg').text("Please enter your Password almost (20)character");
            $('#user-password-msg').removeClass('mt-1 text-danger').addClass('mt-1 text-danger');
            e.preventDefault();
    }else{
         $('#user-password-msg').text("");
          $('#user-password-msg').removeClass('mt-1 text-danger');
    }
}

function validateAdminAddEmail(email,emaillenght,e){
    if(!isvalidPassword(email,emaillenght)){
        $('#user-email-msg').text("Please enter your Email almost (30)character");
            $('#user-email-msg').removeClass('mt-1 text-danger').addClass('mt-1 text-danger');
            e.preventDefault();
    }else{
         $('#user-email-msg').text("");
          $('#user-email-msg').removeClass('mt-1 text-danger');
    }
}

function validateAdminAddDob(dob,doblenght,e){
    if(!isvalidDOB(dob,doblenght)){
        $('#user-dob-msg').text("Please Choose a Date");
        $('#user-dob-msg').addClass('mt-1 text-danger');
          e.preventDefault();
    }else{
         $('#user-dob-msg').text("");
         $('#user-dob-msg').removeClass('mt-1 text-danger');
    }
}

function validateAdminAddBio(bio,biolenght,e){
    if(!isvalidBio(bio,biolenght)){
        $('#user-bio-msg').text("Please enter your bio between (2 to 1000) characters");
        $('#user-bio-msg').addClass('mt-1 text-danger');
          e.preventDefault();
    }else{
         $('#user-bio-msg').text("");
         $('#user-bio-msg').removeClass('mt-1 text-danger');
    }
}

function validateUpdatePassword(password,passlenght,e){
    if(!isvalidPassword(password,passlenght)){
            $('#password-msg').text("Please enter your Password almost (2 to 20)character");
            $('#password-msg').removeClass('mt-1 text-danger').addClass('mt-1 text-danger');
            e.preventDefault();
    }else{
          $('#password-msg').text("");
          $('#password-msg').removeClass('mt-1 text-danger');
    }
}

function validateUpdateConfirmPassword(confirmPassword,confirmlenght,e){
    if(!isvalidPassword(confirmPassword,confirmlenght)){
            $('#confirm-password-msg').text("Please enter your Confirm Password almost (2 to 20)character");
            $('#confirm-password-msg').removeClass('mt-1 text-danger').addClass('mt-1 text-danger');
            e.preventDefault();
    }
    /*else if(!isvalidCorfirmPassword(password,confirmPassword)){
            $('#confirm-password-msg').text("Please password not match... ");
            $('#confirm-password-msg').removeClass('mt-1 text-danger').addClass('mt-1 text-danger');
            e.preventDefault();
    }*/else{
          $('#confirm-password-msg').text("");
          $('#confirm-password-msg').removeClass('mt-1 text-danger');
    }
}

function validateCheckPassword(password,confirmPassword,e){
    if(!isvalidCorfirmPassword(password,confirmPassword)){
            $('#confirm-password-msg').text("Please password not match... ");
            $('#confirm-password-msg').removeClass('mt-1 text-danger').addClass('mt-1 text-danger');
            e.preventDefault();
    }else{
          $('#confirm-password-msg').text("");
          $('#confirm-password-msg').removeClass('mt-1 text-danger');
    }
}

function isvalidCorfirmPassword(password,confirmPassword){
    //return (!(password.trim() === "") && !(confirmPassword.trim() === "")) || (((password.trim()==="") && (confirmPassword.trim()==="")));
    return (password === confirmPassword);
   
}

function validateLoginUsername(username,userlenght,e){
    if(!isvalidUser(username,userlenght)){
            $('#usernamemsg').text("Please enter your Username between (2 to 20) character");
            $('#usernamemsg').removeClass('mt-1 text-danger').addClass('mt-1 text-danger');
            e.preventDefault();
    }else{
          $('#usernamemsg').text("");
          $('#usernamemsg').removeClass('mt-1 text-danger');
    }
}

function validateLoginPassword(password,passlenght,e){
    if(!isvalidPassword(password,passlenght)){
            $('#passwordmsg').text("Please enter your Password between (2 to 20) character");
            $('#passwordmsg').removeClass('mt-1 text-danger').addClass('mt-1 text-danger');
            e.preventDefault();
    }else{
          $('#passwordmsg').text("");
          $('#passwordmsg').removeClass('mt-1 text-danger');
    }
}

function validateUsername(username,userlenght,e){
    if(!isvalidUser(username,userlenght)){
            $('#user-msg').text("Please enter your Username between (2 to 20) characters");
            $('#user-msg').removeClass('mt-1 text-danger').addClass('mt-1 text-danger');
            e.preventDefault();
    }else{
          $('#user-msg').text("");
          $('#user-msg').removeClass('mt-1 text-danger');
    }
}

function isvalidUser(username,userlength){
    console.log("inside username");
    console.log(username);
    console.log(userlength);
    console.log(!(username === ""));
    console.log(userlength >= 2 && userlength <= 20);
    console.log(!(username === "") && (userlength >= 2 && userlength <= 20));
    return (!(username === "") && (userlength >= 2 && userlength <= 20));
}

function validatePassword(password,passlenght,e){
    if(!isvalidPassword(password,passlenght)){
           $('#pass-msg').text("Please enter your password between (2 to 20) characters");
         $('#pass-msg').addClass('mt-1 text-danger');
          e.preventDefault();
    }else{
          $('#pass-msg').text("");
         $('#pass-msg').removeClass('mt-1 text-danger');
    }
}

function isvalidPassword(password,passlenght){
    console.log("inside password");
    console.log(!(password === ""));
    console.log((passlenght >= 2 && passlenght <= 20));
    console.log(!(password === "") && (passlenght >= 2 && passlenght <= 20));
    return (!(password === "") && (passlenght >= 2 && passlenght <= 20));
}

function validateEmail(email,emaillenght,e){
    if(!isvalidEmail(email,emaillenght)){
        $('#email-msg').text("Please enter your email almost (30) characters");
         $('#email-msg').addClass('mt-1 text-danger');
          e.preventDefault();
    }else{
         $('#email-msg').text("");
         $('#email-msg').removeClass('mt-1 text-danger');
    }
}

function isvalidEmail(email,emaillenght){
    return !(email === "") && (emaillenght >= 2 && emaillenght <= 30);
}

function validateDOB(dob,doblenght,e){
    if(!isvalidDOB(dob,doblenght)){
        $('#dob-msg').text("Please Choose a Date");
        $('#dob-msg').addClass('mt-1 text-danger');
          e.preventDefault();
    }else{
         $('#dob-msg').text("");
         $('#dob-msg').removeClass('mt-1 text-danger');
    }
}

function isvalidDOB(dob,doblenght){
     return !(dob==="") && doblenght === 10; 
}
 
function validateBio(bio,biolenght,e){
    if(!isvalidBio(bio,biolenght)){
        $('#bio-msg').text("Please enter your bio between (2 to 1000) characters");
        $('#bio-msg').addClass('mt-1 text-danger');
          e.preventDefault();
    }else{
         $('#bio-msg').text("");
         $('#bio-msg').removeClass('mt-1 text-danger');
    }
} 

function isvalidBio(bio,biolenght){
    return !(bio === "") && biolenght >=2 && biolenght <=1000;
}

function validateAdminCategory(category, categorylenght, e){
    if (!isValidAdminCategory(category, categorylenght)) {
        console.log("inside if");
        $('#category-msg').text("Enter atleast 2 characher and atmost 30 character");
        $('#category-msg').addClass('mt-1 text-danger');
        e.preventDefault();
    } else {
        console.log("inside else");
        $('#category-msg').text("");
        $('#category-msg').removeClass('mt-1 text-danger');
    }
}

function validateAddCategory(category, categorylenght, e){
    if (!isValidAdminCategory(category, categorylenght)) {
         console.log("inside if");
         $('#category-msg').text("Enter atleast 2 characher and atmost 30 character");
         $('#category-msg').addClass('mt-1 text-danger');
        e.preventDefault();
    } else {
        console.log("inside else");
            $('#category-msg').text("");
            $('#category-msg').removeClass('mt-1 text-danger');
            var url = 'Admin';
            var vars = $(this).serialize();
            $.ajax({
                url: url,
                data: vars,
                type: 'POST',
                dataType: 'html',
                success: function (data, textStatus, jqXHR) {
                    console.log(data);
                     $('#addcategory')[0].reset();
                    $('#msg:empty').css('display', 'block');
                    $('#addCategoryModal').modal('hide');
                    $('#msg').replaceWith($('#msg').html(data));
                    $('.alert-dismissible').fadeTo(5000, 500).slideUp(500, function () {
                        $('.alert-dismissible').alert('close');
                    });
                    $('#msg:empty').css('display', 'none');
                }
            });
    }
}

function isValidAdminCategory(category, categorylenght){
     return !(category === "") && categorylenght >= 2 && categorylenght <= 30;
}

function validateProfileName(username,usernamelenght,e){
    if(!isvalidUser(username,usernamelenght)){
            $('#user-profile-msg').text("Please enter your Username between (2 to 20) characters");
            $('#user-profile-msg').removeClass('mt-1 text-danger').addClass('mt-1 text-danger');
            e.preventDefault();
    }else{
          $('#user-profile-msg').text("");
          $('#user-profile-msg').removeClass('mt-1 text-danger');
    }
}

function validateProfileEmail(email,emaillenght,e){
    if(!isvalidEmail(email,emaillenght)){
        $('#email-profile-msg').text("Please enter your email almost (30) characters");
        $('#email-profile-msg').addClass('mt-1 text-danger');
          e.preventDefault();
    }else{
        $('#email-profile-msg').text("");
        $('#email-profile-msg').removeClass('mt-1 text-danger');
    }
}

function validateForgotEmail(email,emaillenght,e){
       if(!isvalidEmail(email,emaillenght)){
        $('#forgot-email-msg').text("Please enter your email almost (30) characters");
        $('#forgot-email-msg').addClass('mt-1 text-danger');
          e.preventDefault();
    }else{
        $('#email-profile-msg').text("");
        $('#email-profile-msg').removeClass('mt-1 text-danger');
    } 
}
        
function validateProfileBio(bio,biolenght,e){
    if(!isvalidBio(bio,biolenght)){
        $('#bio-profile-msg').text("Please enter your bio between (2 to 1000) characters");
        $('#bio-profile-msg').addClass('mt-1 text-danger');
          e.preventDefault();
    }else{
         $('#bio-profile-msg').text("");
         $('#bio-profile-msg').removeClass('mt-1 text-danger');
    }
}