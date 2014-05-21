(function() {
    var tabs = sellInNamespace('polaris.directives.tabs');

    describe('TabsController', function() {
        var scope, ctrl, expectedTabs;

        beforeEach(function() {
            scope = {};
            expectedTabs = [{name: 'Tab 1', url: 'url/1'}, {name: 'Tab 2', url: 'url/2'}, {name: 'Tab 3', url: 'url/3'}];
            ctrl = new tabs.TabsController(scope, expectedTabs);
        });

        describe('constructor', function() {
            it('sets attributes on scope', function() {
                expect(ctrl.scope).toBeDefined();
                expect(ctrl.scope.tabs).toEqual(expectedTabs);
            });
        });
    });
})();
