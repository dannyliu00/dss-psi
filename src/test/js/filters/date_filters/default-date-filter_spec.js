(function() {
    var dateFilters = sellInNamespace('sellIn.filters.dateFilters');

    describe('DefaultDateFilter', function () {

        var defaultDateFilter ;
        var defaultDate = new Date('1900-01-01T00:00:00.000Z');

        beforeEach(function () {
            angular.mock.module('sellIn.filters.dateFilters');

            defaultDateFilter = new dateFilters.DefaultDateFilter(defaultDate);
        });

        it('should return a null when given a null', function () {
            var input = null;
            var expectedResult = null;
            var result = defaultDateFilter(input);
            expect(result).toEqual(expectedResult);
        });

        it('should return a null when given something that is not a date object', function () {
            var input = 'A string that is not a date object';
            var expectedResult = null;
            var result = defaultDateFilter(input);
            expect(result).toEqual(expectedResult);
        });

        it('should return a null when given the default date', function () {
            var input = new Date('1900-01-01T00:00:00.000Z');
            var expectedResult = null;
            var result = defaultDateFilter(input);
            expect(result).toEqual(expectedResult);
        });

        it('should return the same value when given a legit date', function () {
            var input = new Date('2000-01-01T00:00:00.000Z');
            var expectedResult = new Date('2000-01-01T00:00:00.000Z');
            var result = defaultDateFilter(input);
            expect(result).toEqual(expectedResult);
        });
    });
})();