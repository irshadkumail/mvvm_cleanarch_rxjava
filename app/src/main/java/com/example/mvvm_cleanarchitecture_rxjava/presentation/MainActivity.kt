package com.example.mvvm_cleanarchitecture_rxjava.presentation

import android.content.ContentValues
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.magnifier
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mvvm_cleanarchitecture_rxjava.R
import com.example.mvvm_cleanarchitecture_rxjava.presentation.base.BaseActivity
import com.example.mvvm_cleanarchitecture_rxjava.ui.theme.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel>() {

    override val viewModel by viewModels<MainViewModel>()

    @Composable
    override fun GetScreenView() {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { HomeToolbar() }
        ) {
            HomeScreen(
                viewModel = viewModel,
                onError = {
                    finish()
                }
            )
        }
    }
}

@Composable
fun HomeScreen(viewModel: MainViewModel, onError: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (viewModel.loadingState == true)
            CircularProgressIndicator(
                Modifier
                    .testTag("Circular")
                    .size(MaterialTheme.dimens.paddingLarge)
            )
        else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .testTag("lazyColumn"),
                contentPadding = PaddingValues(6.dp),
                content = {
                    items(viewModel.uiList) {
                        HomeListItem(model = it)
                    }
                })
        }

        ErrorDialog(handleError = viewModel.handleError, onDismiss = onError)
    }
}

@Composable
fun HomeListItem(model: HomeListUiStateItem) {

    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp),
        backgroundColor = MaterialTheme.colors.background
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(model.url)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_placeholder),
                contentDescription = "Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RectangleShape)
                    .size(56.dp)
            )
            Spacer(modifier = Modifier.width(MaterialTheme.dimens.paddingExtraSmall))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = model.name,
                    style = MaterialTheme.LocalTypo.heading_3b.copy(blueGrey900)
                )
                Text(
                    text = model.albumName, style = MaterialTheme.LocalTypo.heading_4b.copy(
                        blueGrey500
                    )
                )
                Text(
                    text = model.username,
                    style = MaterialTheme.LocalTypo.body_c.copy(blueGrey500)
                )
            }
        }
    }
}

@Composable
fun ErrorDialog(handleError: String?, onDismiss: () -> Unit) {
    if (handleError != null) {
        AlertDialog(
            modifier = Modifier.testTag("errorDialog"),
            onDismissRequest = onDismiss,
            title = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.error_encountered),
                        style = MaterialTheme.LocalTypo.heading_2a.copy(blueGrey900)
                    )
                }
            }, text = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = handleError,
                        style = MaterialTheme.LocalTypo.heading_3b.copy(blueGrey900),
                        textAlign = TextAlign.Center
                    )
                }
            }, buttons = {
                Row(
                    modifier = Modifier.padding(all = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { onDismiss.invoke() }
                    ) {
                        Text("Dismiss")
                    }
                }
            })
    }
}

@Composable
fun HomeToolbar() {
    TopAppBar(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(text = stringResource(id = R.string.app_name), textAlign = TextAlign.Center)
        }
    }
}
