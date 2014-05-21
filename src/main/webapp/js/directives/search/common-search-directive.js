(function() {
    var commonSearch = sellInNamespace('polaris.directives.commonSearch');

    function CommonSearchDirective() {
        return {
            restrict: 'E',
            templateUrl: 'js/directives/search/common-search-template.html'
        };
    }

    commonSearch.CommonSearchDirective = CommonSearchDirective;
})();