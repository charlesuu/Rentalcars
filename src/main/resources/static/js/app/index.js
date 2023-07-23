var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });

        $('#btn-update').on('click', function () {
            _this.update();
        });

        $('#btn-delete').on('click', function () {
            _this.delete();
        });

        $('#btn-car-save').on('click', function () {
            _this.carSave();
        });

        $('#btn-car-rental').on('click', function () {
            _this.carLoan();
        });

        $('#btn-car-return').on('click', function () {
            _this.carReturn();
        });
    },
    save : function () {
        var data = {
            name: $('#name').val(),
            age: $('#age').val()
        };

        $.ajax({
            type: 'POST',
            url: '/user',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('사용자가 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert('서버 내부 오류입니다.)');
            //alert(JSON.stringify(error));
        });
    },
    update : function () {
        var data = {
            id: $('#id').val(),
            name: $('#name').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/user',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('이름이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert('서버 내부 오류입니다.');
            //alert(JSON.stringify(error));
        });
    },
    delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/user/'+id,
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('사용자가 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert('서버 내부 오류입니다.');
            //alert(JSON.stringify(error));
        });
    },
    carSave : function () {
        var data = {
            name: $('#car-name').val()
        };

        $.ajax({
            type: 'POST',
            url: '/car',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('차량이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert('서버 내부 오류입니다.');
            //alert(JSON.stringify(error));
        });
    },
    carLoan : function () {
        var data = {
            userName: $('#name').val(),
            carName: $('#car-name').val()
        };

        $.ajax({
            type: 'POST',
            url: '/car/rental',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('렌탈 성공 : 렌탈 기록이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert('렌탈 실패 : 등록되지 않았거나, 이미 렌탈 된 차량입니다.');
            //alert(JSON.stringify(error));
        });
    },
    carReturn : function () {//작업 중
        var data = {
            userName: $('#name').val(),
            carName: $('#car-name').val()
        };

        $.ajax({
            type: 'PUT',
            url: '/car/return',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('반납 성공 : 반납 처리 되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert('반납 실패 : 렌탈한 적 없는 차량입니다');
            //alert(JSON.stringify(error));
        });
    }
};

main.init();