(function() {
    var dateFilters = sellInNamespace('sellIn.filters.dateFilters');

    function DashFilter() {
        return function(input) {
            if (input == null || input === '' || input.length === 0 || input === 0) {
                return '-';
            }
            return input;
        };
    }

    dateFilters.DashFilter = DashFilter;
})();