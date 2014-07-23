(function() {
    var dateFilters = sellInNamespace('sellIn.filters.dateFilters');

    function DefaultDateFilter(date) {
        return function(input) {

            if(input == null) return;

            try {
                input.getFullYear();
            } catch(error) {
                // will throw an error if input is not a Date object
                return;
            }

            if(input.toISOString() == date.toISOString()) return;

            return input;
        };
    }

    dateFilters.DefaultDateFilter = DefaultDateFilter;
})();