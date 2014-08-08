(function () {
    var commonPagination = sellInNamespace('polaris.directives.pagination');

    angular.module('polaris.directives.pagination', [])
        .directive('itemsPerPage', commonPagination.ItemsPerPage)
        .directive('pageControls', commonPagination.PageControls);
})();