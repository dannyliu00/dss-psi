(function() {
    var profile = sellInNamespace('sellIn.pages.profile');

    describe('ProfileController', function() {
        var scope, routeParams, location, dealerResource, dealerProfilesResource, expectedDealerId, expectedProfileId;
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

            expectedProfileId = 1234;
            expectedDealerId = 111;
            routeParams = {dealerId: expectedDealerId, profileId: expectedProfileId};
        });

        beforeEach(inject(function($q, $rootScope, $location, dealerResource, dealerProfilesResource) {
            expectedDealerDeferred = $q.defer();
            dealerResource.get.andReturn(expectedDealerDeferred.promise);

            expectedProfileDeferred = $q.defer();
            dealerProfilesResource.get.andReturn(expectedProfileDeferred.promise);

            location = $location;
            scope = {};
        }));

        describe('constructor', function() {
            it('initializes dealer on scope', inject(function(dealerResource, dealerProfilesResource) {
                ctrl = new profile.ProfileController(scope, routeParams, location, dealerResource, dealerProfilesResource);
                var expectedDealer = {dealerId: expectedDealerId};
                var expectedProfile = {profileId: expectedProfileId};
                expect(dealerResource.get).toHaveBeenCalledWith(expectedDealer);
                expect(dealerProfilesResource.get).toHaveBeenCalledWith(expectedProfile);
            }));
        });
    });
})();