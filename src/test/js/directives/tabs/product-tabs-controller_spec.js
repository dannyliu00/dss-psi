(function() {
    var productTabs = sellInNamespace('sellIn.directives.producttabs');

    describe('ProductTabsController', function() {
        var scope, ctrl, expectedProductTabs;

        beforeEach(function() {
            scope = {};
            expectedProductTabs = [{name: 'U.T.'}];
            ctrl = new productTabs.ProductTabsController(scope, expectedProductTabs);
        });

        
        describe('constructor', function() {
            it('sets attributes on scope', function() {
                expect(scope).toBeDefined();
                expect(scope.productTabs).toEqual(expectedProductTabs);
            });
        });
    });
})();