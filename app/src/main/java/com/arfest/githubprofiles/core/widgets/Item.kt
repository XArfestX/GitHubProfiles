package com.arfest.githubprofiles.core.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import com.arfest.githubprofiles.core.design.theme.itemHeight122
import com.arfest.githubprofiles.core.design.theme.itemWidth126
import com.arfest.githubprofiles.core.design.theme.padding10
import com.arfest.githubprofiles.domain.models.User
import kotlin.reflect.KSuspendFunction1

@Composable
fun Item(
    modifier: Modifier = Modifier,
    user: User,
    onClick: (String) -> Unit,
    getProfile: KSuspendFunction1<String, Unit>
) {
    Card(
        modifier = modifier
            .clickable { getProfile }
            .clickable ( onClick = { onClick(user.login) } )

    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                modifier = Modifier
                    .width(itemWidth126)
                    .height(itemHeight122)
                    .padding(top = padding10),
                model = user.image,
                contentDescription = user.login,
            )
            Text(
                text = user.login,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = padding10)
            )
            Text(
                text = user.countFollowers.toString(),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = user.countRepos.toString(),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun ItemPreview() {
//    Item(
//        modifier = Modifier, user = User(
//            login = "Карта ",
//            image = "https://avatars.githubusercontent.com/u/583231?v=4",
//            countFollowers = 0,
//            countRepos = 0
//        )
//    ) {}
//}