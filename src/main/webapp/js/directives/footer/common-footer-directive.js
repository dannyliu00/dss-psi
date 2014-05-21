(function () {
    var commonFooter = sellInNamespace('polaris.directives.commonFooter');

    function CommonFooterDirective() {
        return {
            restrict: 'E',
            templateUrl: 'js/directives/footer/common-footer-template.html'
        };
    }

    commonFooter.CommonFooterDirective = CommonFooterDirective;
})();