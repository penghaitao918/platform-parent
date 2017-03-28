
app.controller("roleListController", function($scope, $window) {
    var EDIT_MODE = 1;
    var ADD_MODE = -1;
    $scope.isShow = false;
    $scope.$on("controller.add", function(event, data) {
        $scope.isShow = true;
        $scope.mode = ADD_MODE;
    });
    $scope.$on("controller.edit", function(event, e) {
        $scope.isShow = true;
        $scope.formData.text = e.formData.text;
        $scope.formData.des = e.formData.des;
        $scope.formData.thumb = e.formData.thumb;
        $scope.formData.url = e.formData.url;
        $scope.mode = e.index;
    });
    $scope.formData = {
        text: "",
        des: "",
        thumb: "",
        url: "",
        time: null,
        comments: 0,
        loves: 0
    };
    $scope.submit = function() {
        var data = {
            formData: {
                text: $scope.formData.text,
                des: $scope.formData.des,
                thumb: $scope.formData.thumb,
                url: $scope.formData.url
            }
        };
        if($scope.mode != ADD_MODE) {
            data.index = $scope.mode;
            $scope.$emit("controller.editData", data);
        } else {
            $scope.$emit("controller.addData", data);
        }

        $scope.close(true);
    }
    $scope.close = function(immediate) {
        if(!immediate) {
            if($scope.formData.text || $scope.formData.des || $scope.formData.thumb || $scope.formData.url) {
                if(!$window.confirm("不保留已更改数据吗？")) {
                    return;
                }
            }
        }

        $scope.formData.text = "";
        $scope.formData.des = "";
        $scope.formData.thumb = "";
        $scope.formData.url = "";
        $scope.isShow = false;
    }
});
