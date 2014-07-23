(function() {
    var dateFilters = sellInNamespace('sellIn.filters.dateFilters');

    describe('DashFilter', function(){
        var dashFilter;

        beforeEach(function(){
            angular.mock.module('sellIn.filters.dateFilters');

            dashFilter = new dateFilters.DashFilter();
        });

        it('returns the input number if the input is non-null', function() {
            var nonNullNumber = 55;

            var result = dashFilter(nonNullNumber);
            expect(result).toEqual(nonNullNumber);
        });

        it('returns a dash if the input is null', function(){
            var nullInput = null;

            var result = dashFilter(nullInput);
            expect(result).toEqual('-');
        });

        it('returns a dash if the input is an empty string', function() {
            var nullInput = '';

            var result = dashFilter(nullInput);
            expect(result).toEqual('-');
        });

        it('returns a dash if the input is an empty object', function() {
            var nullInput = [];

            var result = dashFilter(nullInput);
            expect(result).toEqual('-');
        });

        it('returns a dash if the input is 0', function() {
            var zeroInput = 0;

            var result = dashFilter(zeroInput);
            expect(result).toEqual('-');
        });
    });
})();