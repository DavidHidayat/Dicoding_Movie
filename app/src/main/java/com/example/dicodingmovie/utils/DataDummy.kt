package com.example.dicodingmovie.utils

import com.example.dicodingmovie.data.MovieEntity
import com.example.dicodingmovie.data.TvShowEntity
import com.example.dicodingmovie.data.source.remote.response.MovieResponse
import com.example.dicodingmovie.data.source.remote.response.TvShowResponse

object DataDummy {

    fun generateDummyMovies(): List<MovieEntity> {

        val movies = ArrayList<MovieEntity>()

        movies.add(MovieEntity(
            adult = false,
            backdropPath = "/qi6Edc1OPcyENecGtz8TF0DUr9e.jpg",
            genreIds = arrayListOf(
                27,
                9648,
                53
            ),
            id = 423108,
            originalLanguage = "en",
            originalTitle = "The Conjuring: The Devil Made Me Do It",
            overview = "Paranormal investigators Ed and Lorraine Warren encounter what would become one of the most sensational cases from their files. The fight for the soul of a young boy takes them beyond anything they'd ever seen before, to mark the first time in U.S. history that a murder suspect would claim demonic possession as a defense.",
            popularity = 5355.58,
            posterPath = "/xbSuFiJbbBWCkyCCKIMfuDCA4yV.jpg",
            releaseDate = "2021-05-25",
            title = "The Conjuring: The Devil Made Me Do It",
            video = false,
            voteAverage = 8.3,
            voteCount = 1832
        ))
        movies.add(MovieEntity(
            false,
            "/fPGeS6jgdLovQAKunNHX8l0avCy.jpg",
            arrayListOf(28,12,53,10752),
            567189,
            "en",
            "Tom Clancy's Without Remorse",
            "An elite Navy SEAL uncovers an international conspiracy while seeking justice for the murder of his pregnant wife.",
            2358.971,
            "/rEm96ib0sPiZBADNKBHKBv5bve9.jpg",
            "2021-04-29",
            "Tom Clancy's Without Remorse",
            false,
            7.3,
            952
        ))
        movies.add(MovieEntity(
            false,
            "/ouOojiypBE6CD1aqcHPVq7cJf2R.jpg",
            arrayListOf(53,18,28,9648),
            578701,
            "en",
            "Those Who Wish Me Dead",
            "A young boy finds himself pursued by two assassins in the Montana wilderness with a survival expert determined to protecting him - and a forest fire threatening to consume them all.",
            2025.387,
            "/xCEg6KowNISWvMh8GvPSxtdf9TO.jpg",
            "2021-05-05",
            "Those Who Wish Me Dead",
            false,
            7.2,
            227
        ))
        movies.add(MovieEntity(
            false,
            "/inJjDhCjfhh3RtrJWBmmDqeuSYC.jpg",
            arrayListOf(878,28,18),
            399566,
            "en",
            "Godzilla vs. Kong",
            "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
            1794.564,
            "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
            "2021-03-24",
            "Godzilla vs. Kong",
            false,
            8.1,
            5596
        ))
        movies.add(MovieEntity(
            false,
            "/lkInRiMtLgl9u9xE0By5hqf66K8.jpg",
            arrayListOf(27),
            632357,
            "en",
            "The Unholy",
            "Alice, a young hearing-impaired girl who, after a supposed visitation from the Virgin Mary, is inexplicably able to hear, speak and heal the sick. As word spreads and people from near and far flock to witness her miracles, a disgraced journalist hoping to revive his career visits the small New England town to investigate. When terrifying events begin to happen all around, he starts to question if these phenomena are the works of the Virgin Mary or something much more sinister.",
            1522.122,
            "/b4gYVcl8pParX8AjkN90iQrWrWO.jpg",
            "2021-03-31",
            "The Unholy",
            false,
            5.6,
            96
        ))
        movies.add(MovieEntity(
            false,
            "/lHhc60NXYzswU4TvKSo45nY9Jzs.jpg",
            arrayListOf(16,35,10,10751,12),
            726684,
            "fr",
            "Miraculous World Shanghai, la légende de Ladydragon",
            "To join Adrien in Shanghai, Marinette is going to visit her uncle Wang who is celebrating his anniversary. But, as soon as she arrives in China, her purse gets stolen with Tikki inside, whom she needs to secretly transform into Ladybug! Without money and alone in the immense city, Marinette accepts the help of a young and resourceful girl, Fei. The two girls will ally and discover the existence of a new magical jewel, the Prodigious. Hawk Moth, also present in Shanghai, seeks to finding it since a long time...",
            1179.57,
            "/msI5a9TPnepx47JUb2vl88hb80R.jpg",
            "2021-04-04",
            "Miraculous World Shanghai – The Legend of Ladydragon",
            false,
            7.9,
            322
        ))
        movies.add(MovieEntity(
            false,
            "/6zbKgwgaaCyyBXE4Sun4oWQfQmi.jpg",
            arrayListOf(28,53,80,35),
            615457,
            "en",
            "Nobody",
            "Hutch Mansell, a suburban dad, overlooked husband, nothing neighbor — a \"nobody.\" When two thieves break into his home one night, Hutch's unknown long-simmering rage is ignited and propels him on a brutal path that will uncover dark secrets he fought to leave behind.",
            1395.905,
            "/oBgWY00bEFeZ9N25wWVyuQddbAo.jpg",
            "2021-03-26",
            "Nobody",
            false,
            8.4,
            1569
        ))
        movies.add(MovieEntity(
            false,
            "/xPpXYnCWfjkt3zzE0dpCNME1pXF.jpg",
            arrayListOf(16,28,12,14,18),
            635302,
            "ja",
            "劇場版「鬼滅の刃」無限列車編",
            "Tanjirō Kamado, joined with Inosuke Hashibira, a boy raised by boars who wears a boar's head, and Zenitsu Agatsuma, a scared boy who reveals his true power when he sleeps, boards the Infinity Train on a new mission with the Fire Hashira, Kyōjurō Rengoku, to defeat a demon who has been tormenting the people and killing the demon slayers who oppose it!",
            1262.894,
            "/h8Rb9gBr48ODIwYUttZNYeMWeUU.jpg",
            "2020-10-16",
            "Demon Slayer -Kimetsu no Yaiba- The Movie Mugen Train",
            false,
            8.4,
            987
        ))
        movies.add(MovieEntity(
            false,
            "/7prYzufdIOy1KCTZKVWpjBFqqNr.jpg",
            arrayListOf(16,12,14,10751,28),
            527774,
            "en",
            "Raya and the Last Dragon",
            "Long ago, in the fantasy world of Kumandra, humans and dragons lived together in harmony. But when an evil force threatened the land, the dragons sacrificed themselves to save humanity. Now, 500 years later, that same evil has returned and it’s up to a lone warrior, Raya, to track down the legendary last dragon to restore the fractured land and its divided people.",
            1045.734,
            "/lPsD10PP4rgUGiGR4CCXA6iY0QQ.jpg",
            "2021-03-03",
            "Raya and the Last Dragon",
            false,
            8.2,
            2862
        ))
        movies.add(MovieEntity(
            false,
            "/5Zv5KmgZzdIvXz2KC3n0MyecSNL.jpg",
            arrayListOf(28,53,80),
            634528,
            "en",
            "The Marksman",
            "Jim Hanson’s quiet life is suddenly disturbed by two people crossing the US/Mexico border – a woman and her young son – desperate to flee a Mexican cartel. After a shootout leaves the mother dead, Jim becomes the boy’s reluctant defender. He embraces his role as Miguel’s protector and will stop at nothing to get him to safety, as they go on the run from the relentless assassins.",
            845.003,
            "/6vcDalR50RWa309vBH1NLmG2rjQ.jpg",
            "2021-01-15",
            "The Marksman",
            false,
            7.4,
            474
        ))
        movies.add(MovieEntity(
            false,
            "/mYM8x2Atv4MaLulaV0KVJWI1Djv.jpg",
            arrayListOf(28,80,53),
            804435,
            "en",
            "Vanquish",
            "Victoria is a young mother trying to put her dark past as a Russian drug courier behind her, but retired cop Damon forces Victoria to do his bidding by holding her daughter hostage. Now, Victoria must use guns, guts and a motorcycle to take out a series of violent gangsters—or she may never see her child again.",
            847.85,
            "/AoWY1gkcNzabh229Icboa1Ff0BM.jpg",
            "2021-04-16",
            "Vanquish",
            false,
            6.2,
            95
        ))
        movies.add(MovieEntity(
            false,
            "/ovggmAOu1IbPGTQE8lg4lBasNC7.jpg",
            arrayListOf(878,28,12,53),
            412656,
            "en",
            "Chaos Walking",
            "Two unlikely companions embark on a perilous adventure through the badlands of an unexplored planet as they try to escape a dangerous and disorienting reality, where all inner thoughts are seen and heard by everyone.",
            691.185,
            "/9kg73Mg8WJKlB9Y2SAJzeDKAnuB.jpg",
            "2021-02-24",
            "Chaos Walking",
            false,
            7.1,
            606
        ))
        return movies
    }
    fun generateDummyTvShow(): List<TvShowEntity> {

        val tv_shows = ArrayList<TvShowEntity>()
        tv_shows.add(TvShowEntity(
            backdropPath = "/Afp8OhiO0Ajb3NPoCBvfu2pqaeO.jpg",
            firstAirDate = "2021-06-09",
            id = 84958,
            name = "Loki",
            originalLanguage = "en",
            originalName = "Loki",
            overview = "After stealing the Tesseract during the events of “Avengers: Endgame,” an alternate version of Loki is brought to the mysterious Time Variance Authority, a bureaucratic organization that exists outside of time and space and monitors the timeline. They give Loki a choice: face being erased from existence due to being a “time variant”or help fix the timeline and stop a greater threat.",
            popularity = 2696.861,
            posterPath = "/kEl2t3OhXc3Zb9FBh1AuYzRTgZp.jpg",
            voteAverage = 8.2,
            voteCount = 1851
        ))
        tv_shows.add(TvShowEntity(
            "/jeruqNWhqRqOR1QyqdQdHunrvU5.jpg",
            "2014-10-07",
            60735,
            "The Flash",
            "en",
            "The Flash",
            "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
            1136.942,
            "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
            7.7,
            7659
        ))
        tv_shows.add(TvShowEntity(
            "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
            "2021-03-19",
            88396,
            "The Falcon and the Winter Soldier",
            "en",
            "The Falcon and the Winter Soldier",
            "Following the events of “Avengers Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
            1048.785,
            "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
            7.9,
            5586
        ))
        tv_shows.add(TvShowEntity(
            "/mZjZgY6ObiKtVuKVDrnS9VnuNlE.jpg",
            "2017-09-25",
            71712,
            "The Good Doctor",
            "en",
            "The Good Doctor",
            "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise can a person who doesn't have the ability to relate to people actually save their lives",
            1022.301,
            "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
            8.6,
            8448
        ))
        tv_shows.add(TvShowEntity(
            "/6UH52Fmau8RPsMAbQbjwN3wJSCj.jpg",
            "2021-03-26",
            95557,
            "Invincible",
            "en",
            "Invincible",
            "Mark Grayson is a normal teenager except for the fact that his father is the most powerful superhero on the planet. Shortly after his seventeenth birthday, Mark begins to develop powers of his own and enters into his father’s tutelage.",
            890.964,
            "/yDWJYRAwMNKbIYT8ZB33qy84uzO.jpg",
            8.9,
            1759
        ))
        tv_shows.add(TvShowEntity(
            "/edmk8xjGBsYVIf4QtLY9WMaMcXZ.jpg",
            "2005-03-27",
            1416,
            "Grey's Anatomy",
            "en",
            "Grey's Anatomy",
            "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
            836.668,
            "/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
            8.2,
            6083
        ))
        tv_shows.add(TvShowEntity(
            "/nBrkOZyI75artyizuBFeya48KbO.jpg",
            "2019-03-15",
            86831,
            "Love, Death & Robots",
            "en",
            "Love, Death & Robots",
            "Terrifying creatures, wicked surprises and dark comedy converge in this NSFW anthology of animated stories presented by Tim Miller and David Fincher.",
            761.406,
            "/asDqvkE66EegtKJJXIRhBJPxscr.jpg",
            8.2,
            1066
        ))
        tv_shows.add(TvShowEntity(
            "/ncftkNAjIz2PBbUMY7T0CHVJP8d.jpg",
            "2016-01-25",
            63174,
            "Lucifer",
            "en",
            "Lucifer",
            "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",
            734.614,
            "/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg",
            8.5,
            8620
        ))
        tv_shows.add(TvShowEntity(
            "/qZtAf4Z1lazGQoYVXiHOrvLr5lI.jpg",
            "2017-01-26",
            69050,
            "Riverdale",
            "en",
            "Riverdale",
            "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
            703.146,
            "/wRbjVBdDo5qHAEOVYoMWpM58FSA.jpg",
            8.6,
            11292
        ))
        tv_shows.add(TvShowEntity(
            "/4YKkS95v9o9c0tBVA46xIn6M1tx.jpg",
            "2021-05-07",
            93484,
            "Jupiter's Legacy",
            "en",
            "Jupiter's Legacy",
            "When the world's first generation of superheroes received their powers in the 1930s become the revered elder guard in the present, their superpowered children struggle to live up to the legendary feats of their parents.",
            689.008,
            "/9yxep7oJdkj3Pla9TD9gKflRApY.jpg",
            7.4,
            231
        ))
        return tv_shows
    }

    fun generateRemoteDummyMovies(): List<MovieResponse> {

        val movies = ArrayList<MovieResponse>()

        movies.add(MovieResponse(
            false,
            "/6ELCZlTA5lGUops70hKdB83WJxH.jpg",
            arrayListOf(28,14,12),
            460465,
            "en",
            "Mortal Kombat",
            "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
            2437.17,
            "/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg",
            "2021-04-07",
            "Mortal Kombat",
            false,
            7.6,
            2594
        ))
        movies.add(MovieResponse(
            false,
            "/fPGeS6jgdLovQAKunNHX8l0avCy.jpg",
            arrayListOf(28,12,53,10752),
            567189,
            "en",
            "Tom Clancy's Without Remorse",
            "An elite Navy SEAL uncovers an international conspiracy while seeking justice for the murder of his pregnant wife.",
            2358.971,
            "/rEm96ib0sPiZBADNKBHKBv5bve9.jpg",
            "2021-04-29",
            "Tom Clancy's Without Remorse",
            false,
            7.3,
            952
        ))
        movies.add(MovieResponse(
            false,
            "/ouOojiypBE6CD1aqcHPVq7cJf2R.jpg",
            arrayListOf(53,18,28,9648),
            578701,
            "en",
            "Those Who Wish Me Dead",
            "A young boy finds himself pursued by two assassins in the Montana wilderness with a survival expert determined to protecting him - and a forest fire threatening to consume them all.",
            2025.387,
            "/xCEg6KowNISWvMh8GvPSxtdf9TO.jpg",
            "2021-05-05",
            "Those Who Wish Me Dead",
            false,
            7.2,
            227
        ))
        movies.add(MovieResponse(
            false,
            "/inJjDhCjfhh3RtrJWBmmDqeuSYC.jpg",
            arrayListOf(878,28,18),
            399566,
            "en",
            "Godzilla vs. Kong",
            "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
            1794.564,
            "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
            "2021-03-24",
            "Godzilla vs. Kong",
            false,
            8.1,
            5596
        ))
        movies.add(MovieResponse(
            false,
            "/lkInRiMtLgl9u9xE0By5hqf66K8.jpg",
            arrayListOf(27),
            632357,
            "en",
            "The Unholy",
            "Alice, a young hearing-impaired girl who, after a supposed visitation from the Virgin Mary, is inexplicably able to hear, speak and heal the sick. As word spreads and people from near and far flock to witness her miracles, a disgraced journalist hoping to revive his career visits the small New England town to investigate. When terrifying events begin to happen all around, he starts to question if these phenomena are the works of the Virgin Mary or something much more sinister.",
            1522.122,
            "/b4gYVcl8pParX8AjkN90iQrWrWO.jpg",
            "2021-03-31",
            "The Unholy",
            false,
            5.6,
            96
        ))
        movies.add(MovieResponse(
            false,
            "/lHhc60NXYzswU4TvKSo45nY9Jzs.jpg",
            arrayListOf(16,35,10,10751,12),
            726684,
            "fr",
            "Miraculous World Shanghai, la légende de Ladydragon",
            "To join Adrien in Shanghai, Marinette is going to visit her uncle Wang who is celebrating his anniversary. But, as soon as she arrives in China, her purse gets stolen with Tikki inside, whom she needs to secretly transform into Ladybug! Without money and alone in the immense city, Marinette accepts the help of a young and resourceful girl, Fei. The two girls will ally and discover the existence of a new magical jewel, the Prodigious. Hawk Moth, also present in Shanghai, seeks to finding it since a long time...",
            1179.57,
            "/msI5a9TPnepx47JUb2vl88hb80R.jpg",
            "2021-04-04",
            "Miraculous World Shanghai – The Legend of Ladydragon",
            false,
            7.9,
            322
        ))
        movies.add(MovieResponse(
            false,
            "/6zbKgwgaaCyyBXE4Sun4oWQfQmi.jpg",
            arrayListOf(28,53,80,35),
            615457,
            "en",
            "Nobody",
            "Hutch Mansell, a suburban dad, overlooked husband, nothing neighbor — a \"nobody.\" When two thieves break into his home one night, Hutch's unknown long-simmering rage is ignited and propels him on a brutal path that will uncover dark secrets he fought to leave behind.",
            1395.905,
            "/oBgWY00bEFeZ9N25wWVyuQddbAo.jpg",
            "2021-03-26",
            "Nobody",
            false,
            8.4,
            1569
        ))
        movies.add(MovieResponse(
            false,
            "/xPpXYnCWfjkt3zzE0dpCNME1pXF.jpg",
            arrayListOf(16,28,12,14,18),
            635302,
            "ja",
            "劇場版「鬼滅の刃」無限列車編",
            "Tanjirō Kamado, joined with Inosuke Hashibira, a boy raised by boars who wears a boar's head, and Zenitsu Agatsuma, a scared boy who reveals his true power when he sleeps, boards the Infinity Train on a new mission with the Fire Hashira, Kyōjurō Rengoku, to defeat a demon who has been tormenting the people and killing the demon slayers who oppose it!",
            1262.894,
            "/h8Rb9gBr48ODIwYUttZNYeMWeUU.jpg",
            "2020-10-16",
            "Demon Slayer -Kimetsu no Yaiba- The Movie Mugen Train",
            false,
            8.4,
            987
        ))
        movies.add(MovieResponse(
            false,
            "/7prYzufdIOy1KCTZKVWpjBFqqNr.jpg",
            arrayListOf(16,12,14,10751,28),
            527774,
            "en",
            "Raya and the Last Dragon",
            "Long ago, in the fantasy world of Kumandra, humans and dragons lived together in harmony. But when an evil force threatened the land, the dragons sacrificed themselves to save humanity. Now, 500 years later, that same evil has returned and it’s up to a lone warrior, Raya, to track down the legendary last dragon to restore the fractured land and its divided people.",
            1045.734,
            "/lPsD10PP4rgUGiGR4CCXA6iY0QQ.jpg",
            "2021-03-03",
            "Raya and the Last Dragon",
            false,
            8.2,
            2862
        ))
        movies.add(MovieResponse(
            false,
            "/5Zv5KmgZzdIvXz2KC3n0MyecSNL.jpg",
            arrayListOf(28,53,80),
            634528,
            "en",
            "The Marksman",
            "Jim Hanson’s quiet life is suddenly disturbed by two people crossing the US/Mexico border – a woman and her young son – desperate to flee a Mexican cartel. After a shootout leaves the mother dead, Jim becomes the boy’s reluctant defender. He embraces his role as Miguel’s protector and will stop at nothing to get him to safety, as they go on the run from the relentless assassins.",
            845.003,
            "/6vcDalR50RWa309vBH1NLmG2rjQ.jpg",
            "2021-01-15",
            "The Marksman",
            false,
            7.4,
            474
        ))
        movies.add(MovieResponse(
            false,
            "/mYM8x2Atv4MaLulaV0KVJWI1Djv.jpg",
            arrayListOf(28,80,53),
            804435,
            "en",
            "Vanquish",
            "Victoria is a young mother trying to put her dark past as a Russian drug courier behind her, but retired cop Damon forces Victoria to do his bidding by holding her daughter hostage. Now, Victoria must use guns, guts and a motorcycle to take out a series of violent gangsters—or she may never see her child again.",
            847.85,
            "/AoWY1gkcNzabh229Icboa1Ff0BM.jpg",
            "2021-04-16",
            "Vanquish",
            false,
            6.2,
            95
        ))
        movies.add(MovieResponse(
            false,
            "/ovggmAOu1IbPGTQE8lg4lBasNC7.jpg",
            arrayListOf(878,28,12,53),
            412656,
            "en",
            "Chaos Walking",
            "Two unlikely companions embark on a perilous adventure through the badlands of an unexplored planet as they try to escape a dangerous and disorienting reality, where all inner thoughts are seen and heard by everyone.",
            691.185,
            "/9kg73Mg8WJKlB9Y2SAJzeDKAnuB.jpg",
            "2021-02-24",
            "Chaos Walking",
            false,
            7.1,
            606
        ))
        return movies
    }
    fun generateRemoteDummyTvShow(): List<TvShowResponse> {

        val tv_shows = ArrayList<TvShowResponse>()
        tv_shows.add(TvShowResponse(
            "/dYvIUzdh6TUv4IFRq8UBkX7bNNu.jpg",
            "2021-03-24",
            120168,
            "Who Killed Sara?",
            "es",
            "¿Quién mató a Sara?",
            "Hell-bent on exacting revenge and proving he was framed for his sister's murder, Álex sets out to unearth much more than the crime's real culprit.",
            1606.074,
            "/o7uk5ChRt3quPIv8PcvPfzyXdMw.jpg",
            7.8,
            658
        ))
        tv_shows.add(TvShowResponse(
            "/jeruqNWhqRqOR1QyqdQdHunrvU5.jpg",
            "2014-10-07",
            60735,
            "The Flash",
            "en",
            "The Flash",
            "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
            1136.942,
            "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
            7.7,
            7659
        ))
        tv_shows.add(TvShowResponse(
            "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
            "2021-03-19",
            88396,
            "The Falcon and the Winter Soldier",
            "en",
            "The Falcon and the Winter Soldier",
            "Following the events of “Avengers Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
            1048.785,
            "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
            7.9,
            5586
        ))
        tv_shows.add(TvShowResponse(
            "/mZjZgY6ObiKtVuKVDrnS9VnuNlE.jpg",
            "2017-09-25",
            71712,
            "The Good Doctor",
            "en",
            "The Good Doctor",
            "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise can a person who doesn't have the ability to relate to people actually save their lives",
            1022.301,
            "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
            8.6,
            8448
        ))
        tv_shows.add(TvShowResponse(
            "/6UH52Fmau8RPsMAbQbjwN3wJSCj.jpg",
            "2021-03-26",
            95557,
            "Invincible",
            "en",
            "Invincible",
            "Mark Grayson is a normal teenager except for the fact that his father is the most powerful superhero on the planet. Shortly after his seventeenth birthday, Mark begins to develop powers of his own and enters into his father’s tutelage.",
            890.964,
            "/yDWJYRAwMNKbIYT8ZB33qy84uzO.jpg",
            8.9,
            1759
        ))
        tv_shows.add(TvShowResponse(
            "/edmk8xjGBsYVIf4QtLY9WMaMcXZ.jpg",
            "2005-03-27",
            1416,
            "Grey's Anatomy",
            "en",
            "Grey's Anatomy",
            "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
            836.668,
            "/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
            8.2,
            6083
        ))
        tv_shows.add(TvShowResponse(
            "/nBrkOZyI75artyizuBFeya48KbO.jpg",
            "2019-03-15",
            86831,
            "Love, Death & Robots",
            "en",
            "Love, Death & Robots",
            "Terrifying creatures, wicked surprises and dark comedy converge in this NSFW anthology of animated stories presented by Tim Miller and David Fincher.",
            761.406,
            "/asDqvkE66EegtKJJXIRhBJPxscr.jpg",
            8.2,
            1066
        ))
        tv_shows.add(TvShowResponse(
            "/ncftkNAjIz2PBbUMY7T0CHVJP8d.jpg",
            "2016-01-25",
            63174,
            "Lucifer",
            "en",
            "Lucifer",
            "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",
            734.614,
            "/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg",
            8.5,
            8620
        ))
        tv_shows.add(TvShowResponse(
            "/qZtAf4Z1lazGQoYVXiHOrvLr5lI.jpg",
            "2017-01-26",
            69050,
            "Riverdale",
            "en",
            "Riverdale",
            "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
            703.146,
            "/wRbjVBdDo5qHAEOVYoMWpM58FSA.jpg",
            8.6,
            11292
        ))
        tv_shows.add(
            TvShowResponse(
            "/4YKkS95v9o9c0tBVA46xIn6M1tx.jpg",
            "2021-05-07",
            93484,
            "Jupiter's Legacy",
            "en",
            "Jupiter's Legacy",
            "When the world's first generation of superheroes received their powers in the 1930s become the revered elder guard in the present, their superpowered children struggle to live up to the legendary feats of their parents.",
            689.008,
            "/9yxep7oJdkj3Pla9TD9gKflRApY.jpg",
            7.4,
            231
        )
        )
        return tv_shows
    }

}