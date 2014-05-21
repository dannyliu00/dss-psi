(function() {
    var commonHeader = sellInNamespace('polaris.directives.commonHeader');

    function CommonHeaderDirective() {
        return {
            restrict: 'E',
            controller: commonHeader.CommonHeaderController,
            templateUrl: 'js/directives/header/common_header_template.html'
        };
    }

    commonHeader.CommonHeaderDirective = CommonHeaderDirective;
})();