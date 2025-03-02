package africa.digitalhusters.composewhatsapp.ui.screen.communities

import africa.digitalhusters.composewhatsapp.R
import africa.digitalhusters.composewhatsapp.ui.shared.components.PrimaryButton
import africa.digitalhusters.composewhatsapp.ui.theme.Blue
import africa.digitalhusters.composewhatsapp.ui.theme.ComposeWhatsAppTheme
import africa.digitalhusters.composewhatsapp.ui.theme.Dimensions
import africa.digitalhusters.composewhatsapp.ui.theme.Green
import africa.digitalhusters.composewhatsapp.ui.theme.PaleGrey50
import africa.digitalhusters.composewhatsapp.ui.theme.Typography
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CommunitiesScreen() {
    CommunitiesScreenContent()
}

@Composable
fun CommunitiesScreenContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(horizontal = Dimensions.Medium)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .padding(top = Dimensions.XLarge)
                .size(height = 150.dp, width = 175.dp),
            colors = CardDefaults.cardColors(containerColor = PaleGrey50)
        ) {
            Icon(
                painter = painterResource(R.drawable.icn_groups_filled),
                contentDescription = "",
                tint = Green,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(Modifier.height(Dimensions.Medium))

        Text(
            text = stringResource(R.string.stay_connected_with_a_community_title),
            style = Typography.titleLarge,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(Dimensions.Medium))

        Text(
            text = stringResource(R.string.stay_connected_with_a_community_description),
            style = Typography.labelLarge,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(Dimensions.Medium))

        Text(
            text = stringResource(R.string.see_example_communities_label),
            style = Typography.labelLarge,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Blue
        )

        Spacer(Modifier.height(Dimensions.XLarge))

        PrimaryButton(
            text = stringResource(R.string.start_your_community_button),
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ContentScreenPreview() {
    ComposeWhatsAppTheme {
        Scaffold { contentPadding ->
            CommunitiesScreenContent(modifier = Modifier.padding(contentPadding))
        }
    }
}