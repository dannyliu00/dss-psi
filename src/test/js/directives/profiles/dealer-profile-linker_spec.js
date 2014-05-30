(function() {
    var dealerProfiles = sellInNamespace('sellIn.directives.profiles');

    describe('DealerProfileUILinker', function() {
        var element, attrs, expectedProfile, expectedType;

        beforeEach(function() {
            attrs = {};
            element = {};
            expectedProfile = {};
            expectedType = 'UTType';
            expectedProfile.type = expectedType;
            attrs.profile = expectedProfile;
        });

        it('returns an appropriate template', function() {
            var result = dealerProfiles.ProfileUILinker(element, attrs);
            var basePath = 'js/directives/profiles/';
            var pathSuffix = '-template.html';

            expect(result).toEqual(basePath + expectedType + pathSuffix);
        });
    });
})();
