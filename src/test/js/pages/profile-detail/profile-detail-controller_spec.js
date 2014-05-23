(function() {
    var profile = sellInNamespace('sellIn.pages.profile');

    describe('ProfileController', function() {
        var scope, routeParams, dealerResource, dealerProfilesResource, expectedDealerId, expectedProfileId;
        var expectedDealerDeferred, expectedProfileDeferred;
        var ctrl;

        beforeEach(function() {
            angular.mock.module('sellIn.pages.profile');

            angular.mock.module(function($provide) {
                dealerResource = jasmine.createSpyObj('dealerResource', ['get']);
                $provide.decorator('dealerResource', [function() {
                    return dealerResource;
                }]);
                dealerProfilesResource = jasmine.createSpyObj('dealerProfilesResource', ['get']);
                $provide.decorator('dealerProfilesResource', [function() {
                    return dealerProfilesResource;
                }]);
            });

            expectedProfileId = 999;
            expectedDealerId = 111;
            routeParams = {dealerId: expectedDealerId, profileId: expectedProfileId};
        });

        beforeEach(inject(function($q, dealerResource, dealerProfilesResource) {
            expectedDealerDeferred = $q.defer();
            dealerResource.get.andReturn(expectedDealerDeferred.promise);

            expectedProfileDeferred = $q.defer();
            dealerProfilesResource.get.andReturn(expectedProfileDeferred.promise);

            scope = {};
        }));

        describe('constructor', function() {
            it('initializes dealer on scope', inject(function(dealerResource, dealerProfilesResource) {
                ctrl = new profile.ProfileController(scope, routeParams, dealerResource, dealerProfilesResource);
                expect(dealerResource.get).toHaveBeenCalled();
                expect(dealerProfilesResource.get).toHaveBeenCalled();
                expect(scope.dealer).toBeDefined();
                expect(scope.profile).toBeDefined();
            }));
        });
    });
})();