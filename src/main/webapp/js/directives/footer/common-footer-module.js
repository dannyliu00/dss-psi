(function () {
    var commonFooter = sellInNamespace('polaris.directives.commonFooter');

    angular.module('polaris.directives.commonFooter', [])
        .directive('commonFooter', commonFooter.CommonFooterDirective);

})();