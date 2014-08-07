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

    describe('queryCurrent', function() {
        it('returns a promise with a list of profiles of a specified dealer', inject(function(dealerProfilesResource, currentProfilesUrl) {
            var r = 12345;
            var dealer = {r: r};
            var expectedRequest = currentProfilesUrl;
            expectedRequest += '?r=' + r;
            var expectedList = [{name: 'U.T. Victory Profile'}];

            httpBackend.when('GET', expectedRequest).respond(expectedList);

            var promise = dealerProfilesResource.queryCurrent(dealer);

            promise.then(function(actualList) {
                expect(actualList.length).toEqual(expectedList.length);
            });

            httpBackend.flush();
        }));
    });

    describe('queryHistory', function() {
        it('returns a promise with a list of profiles of a specified dealer', inject(function(dealerProfilesResource, historyProfilesUrl) {
            var r = 12345;
            var dealer = {r: r};
            var expectedRequest = historyProfilesUrl;
            expectedRequest += '?r=' + r;
            var expectedList = [{name: 'U.T. Victory Profile'}];

            httpBackend.when('GET', expectedRequest).respond(expectedList);

            var promise = dealerProfilesResource.queryHistory(dealer);

            promise.then(function(actualList) {
                expect(actualList.length).toEqual(expectedList.length);
            });

            httpBackend.flush();
        }));
    });
});
