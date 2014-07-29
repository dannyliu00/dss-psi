(function() {
    var tabs = sellInNamespace('polaris.directives.tabs');

    describe('TabsController', function() {
        var scope, ctrl, expectedTabs, expectedLastTab;

        beforeEach(function() {
            angular.mock.module('polaris.directives.tabs');

            expectedTabs = [
                {name: 'Tab 1', url: 'current', isActive: 'active'},
                {name: 'Tab 2', url: 'history', isActive: ''},
                {name: 'Tab 3', url: 'archived', isActive: ''}];

        });

        beforeEach(inject(function($rootScope) {
            scope = $rootScope.$new();
        }));

        describe('constructor', function() {
            it('sets tab attributes on scope with current as active', function() {
                expectedLastTab = {profilesTab: ''};

                ctrl = new tabs.TabsController(scope, expectedTabs, expectedLastTab);

                expect(scope.tabs.length).toEqual(expectedTabs.length);
                expect(scope.tabs[0].isActive).toEqual('active');
                expect(scope.tabs[1].isActive).toEqual('');
                expect(scope.tabs[2].isActive).toEqual('');
            });
        });
    });
})();
