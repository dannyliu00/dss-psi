describe('DealerProfilesResource', function() {
    var httpBackend;

    beforeEach(function() {
        angular.mock.module('sellIn.resources.dealerProfiles');

        angular.mock.inject(function($injector) {
            httpBackend = $injector.get('$httpBackend');
        });
    });

    afterEach(function() {
        httpBackend.verifyNoOutstandingExpectation();
        httpBackend.verifyNoOutstandingRequest();
    });

    describe('get', function() {
        it('returns a promise with a single profile of a specified dealer', inject(function(dealerProfilesResource, profilesUrl) {
            var profileId = 999;
            var profile = {profileId: profileId};
            var expectedRequest = profilesUrl.replace(':profileId', profileId);
            var expectedProfile = { name: 'U.T. Victory Profile'};

            httpBackend.when('GET', expectedRequest).respond(expectedProfile);
            httpBackend.expectGET(expectedRequest);

            var promise = dealerProfilesResource.get(profile);

            promise.then(function(actualProfile) {
                expect(actualProfile.name).toEqual(expectedProfile.name);
            });

            httpBackend.flush();
        }));
    });

});
