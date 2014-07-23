(function() {
    var dateFilters = sellInNamespace('sellIn.filters.dateFilters');

    function StringToDateFilter() {
        return function (dateString) {

            //takes in a date string with  one of the following formats

            //UTC Time formats
            //12/10/2013 17:38:30 GMT
            //Tue, 10 Dec 2013 17:38:30 GMT
            //2013-12-10T17:49:22.750Z

            //Local time formats
            //12/10/2013 17:38:30
            //Tue, 10 Dec 2013 17:38:30
            //2013-12-10T17:49:22.750

            if ((typeof dateString != 'string') || (dateString === '') || (dateString === null)) {
                return null;
            }

            var milliseconds = Date.parse(dateString);

            if (isNaN(milliseconds)) {
                return null;
            }

            var date = new Date(milliseconds);
            return date;
        };
    }

    dateFilters.StringToDateFilter = StringToDateFilter;
})();