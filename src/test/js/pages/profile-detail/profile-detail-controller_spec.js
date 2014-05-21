(function() {

    describe('ProfileController', function() {
        var scope, routeParams, dealerResource, profileResource, expectedDealerId, expectedProfileId;
        var expectedDealerDeferred, expectedProfileDeferred;
        var controller, ctrl;

        beforeEach(function() {
            angular.mock.module('sellIn.pages.profile');

            angular.mock.module(function($provide) {
                dealerResource = jasmine.createSpyObj('dealerResource', ['get']);
                $provide.decorator('dealerResource', [function() {
                    return dealerResource;
                }]);
                profileResource = jasmine.createSpyObj('profileResource', ['get']);
                $provide.decorator('profileResource', [function() {
                    return profileResource;
                }]);
            });

            expectedProfileId = 999;
            expectedDealerId = 111;
            routeParams = {dealerId: expectedDealerId, profileId: expectedProfileId};

            angular.mock.inject(function($injector) {
                var controller = $injector.get('$controller');
                ctrl = controller('sellIn.pages.profile.ProfileController', {$scope: scope});
            });
        });

        beforeEach(inject(function($rootScope, dealerResource, profileResource, $q) {
            expectedDealerDeferred = $q.defer();
            dealerResource.get.andReturn(expectedDealerDeferred.promise);

            expectedProfileDeferred = $q.defer();
            profileResource.get.andReturn(expectedProfileDeferred.promise);

            scope = $rootScope.new();
        }));

        describe('constructor', function() {
            it('initializes dealer on scope', inject(function($rootScope, dealerResource) {
                ctrl = new profile.ProfileController(scope, routeParams, dealerResource, profileResource);
                expect(dealerResource.get).toHaveBeenCalled();
                expect(scope.dealer).toBeDefined();
                expect(scope.profile).toBeDefined();
            }));
        });
    });
})();