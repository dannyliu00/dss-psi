(function() {
    var commonPagination = sellInNamespace('polaris.directives.pagination');

    function ItemsPerPage() {
        return {
            restrict: 'E',
            templateUrl: 'js/directives/pagination/items-per-page-template.html'
        };
    };

    commonPagination.ItemsPerPage = ItemsPerPage;
})();