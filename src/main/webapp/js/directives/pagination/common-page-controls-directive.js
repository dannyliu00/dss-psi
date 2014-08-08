(function () {
    var commonPagination = sellInNamespace('polaris.directives.pagination');

    function PageControls() {
        return {
            restrict: 'E',
            templateUrl: 'js/directives/pagination/page-controls.html'
        };
    }

    commonPagination.PageControls = PageControls;
})();