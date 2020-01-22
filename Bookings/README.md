I have to apologize about this, but I spent quite some time playing around with the 
JSON for both Instant and OffsetDateTime formats and in the end came up with custom 
deserializers and serializers for both (an annotation approach wasn't working and 
Spring kept complaining about it); so here's a disclaimer:

For all timestamp fields please input time in the format "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS",
otherwise Spring will complain about a misconfigured timestamp