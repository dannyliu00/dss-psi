(function() {
    var profile = sellInNamespace('sellIn.pages.profile');

    describe('ProfileController', function() {
        var scope, routeParams, location, dealerResource, dealerProfileResource, expectedDealerId, expectedProfileId;
        var expectedDealerDeferred, expectedDealer, expectedProfileDeferred, expectedProfile;
        var ctrl;

        beforeEach(function() {
            angular.mock.module('sellIn.pages.profile');

            angular.mock.module(function($provide) {
                dealerResource = jasmine.createSpyObj('dealerResource', ['get']);
                $provide.decorator('dealerResource', [function() {
                    return dealerResource;
                }]);
                dealerProfileResource = jasmine.createSpyObj('dealerProfileResource', ['get']);
                $provide.decorator('dealerProfileResource', [function() {
                    return dealerProfileResource;
                }]);
            });

            expectedProfileId = 1234;
            expectedDealerId = 111;
            routeParams = {dealerId: expectedDealerId, profileId: expectedProfileId};
        });

        beforeEach(inject(function($q, $rootScope, $location, dealerResource, dealerProfileResource) {
            expectedDealerDeferred = $q.defer();
            dealerResource.get.andReturn(expectedDealerDeferred.promise);
            expectedDealer = {
                name: 'U.T. Dealer',
                dealerId: 999
            };

            expectedProfileDeferred = $q.defer();
            dealerProfileResource.get.andReturn(expectedProfileDeferred.promise);
            expectedProfile = {
                name: 'U.T. Profile',
                segments: [{
                    name: 'U.T. Segment Name',
                    orderSegments: [{
                        name: 'U.T. OrderSegment Name'
                    }]
                }]
            };

            location = $location;
            scope = $rootScope.$new();
        }));

        describe('constructor', function() {
            it('initializes dealer on scope', inject(function($rootScope, dealerResource, dealerProfileResource) {
                ctrl = new profile.ProfileController(scope, routeParams, location, dealerResource, dealerProfileResource);
                var expectedDealer = {dealerId: expectedDealerId};
                var expectedProfile = {profileId: expectedProfileId};
                expect(dealerResource.get).toHaveBeenCalledWith(expectedDealer);
                expect(dealerProfileResource.get).toHaveBeenCalledWith(expectedProfile);
            }));
        });
    });
})();