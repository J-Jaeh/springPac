let main = {
    init: function () {
        let _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });

        $('#btn-update').on('click', function () {
            _this.update();
        });

        $('#btn-delete').on('click', function () {
            _this.delete();
        });
        $('#login-id-submit').on('click', function () {
            _this.login();
        })
    },
    save: function () {
        let data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update: function () {
        let data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        let id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete: function () {
        let id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function () {
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    login : function (){

        let username = $('#login-id').val();
        let password = $('#login-password').val();

        $.ajax({
            type: "POST",
            url: `/api/user/login`,
            contentType: "application/json",
            data: JSON.stringify({username: username, password: password}),
            success: function (response, status, xhr) {
                if(response === 'success') {
                    let host = window.location.host;
                    let url = host + '/';

                    document.cookie =
                        'Authorization' + '=' + xhr.getResponseHeader('Authorization') + ';path=/';
                    window.location.href = 'http://' + url;
                } else {
                    alert('로그인에 실패하셨습니다. 다시 로그인해 주세요.')
                    window.location.reload();
                }
            }
        })
    }

};

main.init();