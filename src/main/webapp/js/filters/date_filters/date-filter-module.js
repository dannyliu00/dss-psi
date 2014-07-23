(function() {
    var dateFilters = sellInNamespace('sellIn.filters.dateFilters');

    angular.module('sellIn.filters.dateFilters', [])
        .filter('stringToDate', dateFilters.StringToDateFilter)
        .filter('defaultDate', dateFilters.DefaultDateFilter)
        .filter('dash', dateFilters.DashFilter)
        .constant('date', new Date('1900-01-01T06:00:00.000Z'));

})();