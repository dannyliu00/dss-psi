(function () {
    var commonSearch = sellInNamespace('polaris.directives.commonSearch');

    angular.module('polaris.directives.commonSearch', [])
        .directive('commonSearch', commonSearch.CommonSearchDirective);

})();