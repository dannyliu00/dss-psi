(function () {
    var dateFilters = sellInNamespace('sellIn.filters.dateFilters');

    describe('StringToDateFilter', function () {

        var stringToDateFilter;

        beforeEach(function () {
            angular.mock.module('sellIn.filters.dateFilters');

            stringToDateFilter = new dateFilters.StringToDateFilter();
        });

        it('returns the date in javascript date object', function () {
            var testDate = '2014-01-01T00:00:00.000Z';

            var result = stringToDateFilter(testDate);
            var expectedDate = new Date(testDate);
            expect(result).toEqual(expectedDate);
        });

        it('returns null for a null date', function () {
            var testDate = null;
            var result = stringToDateFilter(testDate);
            expect(result).toEqual(null);
        });

        it('returns null for a non-string', function () {
            var testDate = [];
            var result = stringToDateFilter(testDate);
            expect(result).toEqual(null);
        });

        it('returns null for a string that is not a valid date', function () {
            var testDate = 'This is an invalid date string';
            var result = stringToDateFilter(testDate);
            expect(result).toEqual(null);
        });

        it('returns null for an empty string', function () {
            var testDate = '';
            var result = stringToDateFilter(testDate);
            expect(result).toEqual(null);
        });
    });
})();