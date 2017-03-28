
/*
验证：只能输入整数
 */
var INTEGER_REGEXP = /^\-?\d*$/;
adminApp.directive('integer', function() {
    return {
        require : 'ngModel',
        link : function(scope, elm, attrs, ctrl) {
            ctrl.$parsers.unshift(function(viewValue) {
                if (INTEGER_REGEXP.test(viewValue)) {
                    ctrl.$setValidity('integer', true);
                    return viewValue;
                } else {
                    ctrl.$setValidity('integer', false);
                    return undefined;
                }
            });
        }
    };
});
/*
 验证：只能输入字母
 */

var LETTER_REGEXP = /[^a-zA-Z]/g;
adminApp.directive('letter', function() {
    return {
        require : 'ngModel',
        link : function(scope, elm, attrs, ctrl) {
            ctrl.$parsers.unshift(function(viewValue) {
                if (LETTER_REGEXP.test(viewValue)) {
                    ctrl.$setValidity('letter', false);
                    return undefined;
                } else {
                    ctrl.$setValidity('letter', true);
                    return viewValue;
                }
            });
        }
    };
});
/*
 验证：只能输入字母跟整数
 */

var ALPHANUM_REGEXP = /[\W]/g;
adminApp.directive('alphanum', function() {
    return {
        require : 'ngModel',
        link : function(scope, elm, attrs, ctrl) {
            ctrl.$parsers.unshift(function(viewValue) {
                if (ALPHANUM_REGEXP.test(viewValue)) {
                    ctrl.$setValidity('alphanum', false);
                    return undefined;
                } else {
                    ctrl.$setValidity('alphanum', true);
                    return viewValue;
                }
            });
        }
    };
});



/*
 验证：ip
 */

var IPADDRESS_REGEXP = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
adminApp.directive('ipaddress', function() {
    return {
        require : 'ngModel',
        link : function(scope, elm, attrs, ctrl) {
            ctrl.$parsers.unshift(function(viewValue) {
                if (IPADDRESS_REGEXP.test(viewValue)) {
                    ctrl.$setValidity('ipaddress', true);
                    return viewValue;
                } else {
                    ctrl.$setValidity('ipaddress', false);
                    return undefined;
                }
            });
        }
    };
});


/*
 验证：端口
 */

var PORT_REGEXP =/^([0-9]|[1-9]\d|[1-9]\d{2}|[1-9]\d{3}|[1-5]\d{4}|6[0-4]\d{3}|65[0-4]\d{2}|655[0-2]\d|6553[0-5])$/;
adminApp.directive('port', function() {
    return {
        require : 'ngModel',
        link : function(scope, elm, attrs, ctrl) {
            ctrl.$parsers.unshift(function(viewValue) {
                if (PORT_REGEXP.test(viewValue)) {
                    ctrl.$setValidity('port', true);
                    return viewValue;
                } else {
                    ctrl.$setValidity('port', false);
                    return undefined;
                }
            });
        }
    };
});


/*
 验证：url
 */

var URL_REGEXP =/[^\w\.\/]/ig;
adminApp.directive('url', function() {
    return {
        require : 'ngModel',
        link : function(scope, elm, attrs, ctrl) {
            ctrl.$parsers.unshift(function(viewValue) {
                if (URL_REGEXP.test(viewValue)) {
                    ctrl.$setValidity('url', false);
                    return undefined;
                } else {
                    ctrl.$setValidity('url', true);
                    return viewValue;
                }
            });
        }
    };
});



/*
 验证：手机号
 */

var PHONE_REGEXP =/^1[3|4|5|7|8]\d{9}$/;
adminApp.directive('phone', function() {
    return {
        require : 'ngModel',
        link : function(scope, elm, attrs, ctrl) {
            ctrl.$parsers.unshift(function(viewValue) {
                if (PHONE_REGEXP.test(viewValue)) {
                    ctrl.$setValidity('phone', true);
                    return viewValue;
                } else {
                    ctrl.$setValidity('phone', false);
                    return undefined;

                }
            });
        }
    };
});



/*
 验证：邮箱
 */

var EMAIL_REGEXP =/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
adminApp.directive('email', function() {
    return {
        require : 'ngModel',
        link : function(scope, elm, attrs, ctrl) {
            ctrl.$parsers.unshift(function(viewValue) {
                if (EMAIL_REGEXP.test(viewValue)) {
                    ctrl.$setValidity('email', true);
                    return viewValue;
                } else {
                    ctrl.$setValidity('email', false);
                    return undefined;

                }
            });
        }
    };
});