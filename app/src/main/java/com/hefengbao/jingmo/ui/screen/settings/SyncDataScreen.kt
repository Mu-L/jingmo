/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.settings

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleDown
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material.icons.filled.Download
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.common.network.Result
import com.hefengbao.jingmo.data.model.Dataset
import com.hefengbao.jingmo.data.model.DatasetVersion
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import com.hefengbao.jingmo.ui.screen.settings.components.SettingsTitle

@Composable
fun DataRoute(
    viewModel: DataViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.getDataset()
    }

    val datasetPref by viewModel.datasetPref.collectAsState(initial = DatasetVersion())
    val datasetResult by viewModel.datasetResult.collectAsState(initial = Result.Loading)
    val chinaWorldCultureHeritageResult by viewModel.chinaWorldCultureHeritageResult.collectAsState(
        initial = SyncStatus.NonStatus
    )
    val chinaWorldCultureHeritageProgress by viewModel.chinaWorldCultureHeritageProgress.collectAsState(
        initial = 0f
    )
    val chineseAntitheticalCoupletResult by viewModel.chineseAntitheticalCoupletResult.collectAsState(
        initial = SyncStatus.NonStatus
    )
    val chineseAntitheticalCoupletProgress by viewModel.chineseAntitheticalCoupletProgress.collectAsState(
        initial = 0f
    )
    val chineseExpressionResult by viewModel.chineseExpressionResult.collectAsState(initial = SyncStatus.NonStatus)
    val chineseExpressionResultProgress by viewModel.chineseExpressionResultProgress.collectAsState(
        initial = 0f
    )
    val chineseKnowledgeResult by viewModel.chineseKnowledgeResult.collectAsState(initial = SyncStatus.NonStatus)
    val chineseKnowledgeResultProgress by viewModel.chineseKnowledgeResultProgress.collectAsState(
        initial = 0f
    )
    val chineseModernPoetryResult by viewModel.chineseModernPoetryResult.collectAsState(SyncStatus.NonStatus)
    val chineseModernPoetryResultProgress by viewModel.chineseModernPoetryResultProgress.collectAsState(
        0f
    )
    val chineseProverbResult by viewModel.chineseProverbResult.collectAsState(initial = SyncStatus.NonStatus)
    val chineseProverbResultProgress by viewModel.chineseProverbResultProgress.collectAsState(
        initial = 0f
    )
    val chineseQuotesResult by viewModel.chineseQuotesResult.collectAsState(initial = SyncStatus.NonStatus)
    val chineseQuotesResultProgress by viewModel.chineseQuotesResultProgress.collectAsState(initial = 0f)
    val chineseWisecracksResult by viewModel.chineseWisecracksResult.collectAsState(initial = SyncStatus.NonStatus)
    val chineseWisecracksResultProgress by viewModel.chineseWisecracksResultProgress.collectAsState(
        initial = 0f
    )
    val classicPoemsResult by viewModel.classicPoemsResult.collectAsState(initial = SyncStatus.NonStatus)
    val classicPoemsResultProgress by viewModel.classicPoemsResultProgress.collectAsState(initial = 0f)
    val dictionaryResult by viewModel.dictionaryResult.collectAsState(initial = SyncStatus.NonStatus)
    val dictionaryResultProgress by viewModel.dictionaryResultProgress.collectAsState(initial = 0f)
    val idiomsResult by viewModel.idiomsResult.collectAsState(initial = SyncStatus.NonStatus)
    val idiomsResultProgress by viewModel.idiomsResultProgress.collectAsState(initial = 0f)
    val lyricResult by viewModel.lyricResult.collectAsState(initial = SyncStatus.NonStatus)
    val lyricResultProgress by viewModel.lyricResultProgress.collectAsState(initial = 0f)
    val peopleResult by viewModel.peopleResult.collectAsState(initial = SyncStatus.NonStatus)
    val peopleResultProgress by viewModel.peopleResultProgress.collectAsState(initial = 0f)
    val poemSentencesResult by viewModel.poemSentencesResult.collectAsState(initial = SyncStatus.NonStatus)
    val poemSentencesResultProgress by viewModel.poemSentencesResultProgress.collectAsState(initial = 0f)
    val riddlesResult by viewModel.riddlesResult.collectAsState(initial = SyncStatus.NonStatus)
    val riddlesResultProgress by viewModel.riddlesResultProgress.collectAsState(initial = 0f)
    val tongueTwistersResult by viewModel.tongueTwisterResult.collectAsState(initial = SyncStatus.NonStatus)
    val tongueTwistersResultProgress by viewModel.tongueTwistersResultProgress.collectAsState(
        initial = 0f
    )
    val writingsResult by viewModel.writingsResult.collectAsState(initial = SyncStatus.NonStatus)
    val writingsResultProgress by viewModel.writingsResultProgress.collectAsState(initial = 0f)

    DataScreen(
        onBackClick = onBackClick,
        datasetPref = datasetPref,
        datasetResult = datasetResult,
        syncChinaWorldCultureHeritage = { total: Int, version: Int ->
            viewModel.syncChinaWorldCultureHeritage(total, version)
        },
        chinaWorldCultureHeritageResult = chinaWorldCultureHeritageResult,
        chinaWorldCultureHeritageProgress = chinaWorldCultureHeritageProgress,
        syncChineseAntitheticalCouplet = { total: Int, version: Int ->
            viewModel.syncChineseAntitheticalCouplet(total, version)
        },
        chineseAntitheticalCoupletResult = chineseAntitheticalCoupletResult,
        chineseAntitheticalCoupletProgress = chineseAntitheticalCoupletProgress,
        syncChineseExpression = { total: Int, version: Int ->
            viewModel.syncChineseExpression(total, version)
        },
        chineseExpressionResult = chineseExpressionResult,
        chineseExpressionResultProgress = chineseExpressionResultProgress,
        syncChineseKnowledge = { total: Int, version: Int ->
            viewModel.syncChineseKnowledge(total, version)
        },
        chineseKnowledgeResult = chineseKnowledgeResult,
        chineseKnowledgeResultProgress = chineseKnowledgeResultProgress,
        syncChineseModernPoetry = viewModel::syncChineseModernPoetry,
        chineseModernPoetryResult = chineseModernPoetryResult,
        chineseModernPoetryResultProgress = chineseModernPoetryResultProgress,
        syncChineseProverb = { total: Int, version: Int ->
            viewModel.syncChineseProverbs(total = total, version = version)
        },
        chineseProverbResult = chineseProverbResult,
        chineseProverbResultProgress = chineseProverbResultProgress,
        syncChineseQuotes = viewModel::syncChineseQuotes,
        chineseQuotesResult = chineseQuotesResult,
        chineseQuotesResultProgress = chineseQuotesResultProgress,
        syncChineseWisecracks = { total: Int, version: Int ->
            viewModel.syncChineseWisecracks(total, version)
        },
        chineseWisecracksResult = chineseWisecracksResult,
        chineseWisecracksResultProgress = chineseWisecracksResultProgress,
        syncDictionary = { total: Int, version: Int ->
            viewModel.syncDictionary(total, version)
        },
        dictionaryResult = dictionaryResult,
        dictionaryResultProgress = dictionaryResultProgress,
        syncIdioms = { total: Int, version: Int -> viewModel.syncIdioms(total, version) },
        idiomsResult = idiomsResult,
        idiomsResultProgress = idiomsResultProgress,
        syncLyric = { total: Int, version: Int -> viewModel.syncLyric(total, version) },
        lyricResult = lyricResult,
        lyricResultProgress = lyricResultProgress,
        syncPeople = { total: Int, version: Int -> viewModel.syncPeople(total, version) },
        peopleResult = peopleResult,
        peopleResultProgress = peopleResultProgress,
        syncClassicPoems = { total: Int, version: Int ->
            viewModel.syncClassicPoems(total, version)
        },
        classicPoemsResult = classicPoemsResult,
        classicPoemsResultProgress = classicPoemsResultProgress,
        syncPoemSentences = { total: Int, version: Int ->
            viewModel.syncPoemSentences(total, version)
        },
        poemSentencesResult = poemSentencesResult,
        poemSentencesResultProgress = poemSentencesResultProgress,
        syncRiddles = { total: Int, version: Int -> viewModel.syncRiddles(total, version) },
        riddlesResult = riddlesResult,
        riddlesResultProgress = riddlesResultProgress,
        syncTongueTwisters = { total: Int, version: Int ->
            viewModel.syncTongueTwisters(total, version)
        },
        tongueTwistersResult = tongueTwistersResult,
        tongueTwistersResultProgress = tongueTwistersResultProgress,
        syncWritings = { total: Int, version: Int -> viewModel.syncWritings(total, version) },
        writingsResult = writingsResult,
        writingsResultProgress = writingsResultProgress,
        setWritingsPreviousPage = { viewModel.setWritingsPreviousPage(it) },
        setWritingsPreviousCount = { count: Int, total: Int ->
            viewModel.setWritingsPreviousCount(count, total)
        }
    )
}

@Composable
private fun DataScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    datasetPref: DatasetVersion,
    datasetResult: Result<List<Dataset>>,
    syncChinaWorldCultureHeritage: (total: Int, version: Int) -> Unit,
    chinaWorldCultureHeritageResult: SyncStatus<Any>,
    chinaWorldCultureHeritageProgress: Float,
    syncChineseAntitheticalCouplet: (total: Int, version: Int) -> Unit,
    chineseAntitheticalCoupletResult: SyncStatus<Any>,
    chineseAntitheticalCoupletProgress: Float,
    syncChineseExpression: (total: Int, version: Int) -> Unit,
    chineseExpressionResult: SyncStatus<Any>,
    chineseExpressionResultProgress: Float,
    syncChineseKnowledge: (total: Int, version: Int) -> Unit,
    chineseKnowledgeResult: SyncStatus<Any>,
    chineseKnowledgeResultProgress: Float,
    syncChineseWisecracks: (total: Int, version: Int) -> Unit,
    chineseWisecracksResult: SyncStatus<Any>,
    chineseWisecracksResultProgress: Float,
    syncChineseModernPoetry: (total: Int, version: Int) -> Unit,
    chineseModernPoetryResult: SyncStatus<Any>,
    chineseModernPoetryResultProgress: Float,
    syncChineseProverb: (total: Int, version: Int) -> Unit,
    chineseProverbResult: SyncStatus<Any>,
    chineseProverbResultProgress: Float,
    syncChineseQuotes: (total: Int, version: Int) -> Unit,
    chineseQuotesResult: SyncStatus<Any>,
    chineseQuotesResultProgress: Float,
    syncClassicPoems: (total: Int, version: Int) -> Unit,
    classicPoemsResult: SyncStatus<Any>,
    classicPoemsResultProgress: Float,
    syncDictionary: (total: Int, version: Int) -> Unit,
    dictionaryResult: SyncStatus<Any>,
    dictionaryResultProgress: Float,
    syncIdioms: (total: Int, version: Int) -> Unit,
    idiomsResult: SyncStatus<Any>,
    idiomsResultProgress: Float,
    syncLyric: (total: Int, version: Int) -> Unit,
    lyricResult: SyncStatus<Any>,
    lyricResultProgress: Float,
    syncPeople: (total: Int, version: Int) -> Unit,
    peopleResult: SyncStatus<Any>,
    peopleResultProgress: Float,
    syncPoemSentences: (total: Int, version: Int) -> Unit,
    poemSentencesResult: SyncStatus<Any>,
    poemSentencesResultProgress: Float,
    syncRiddles: (total: Int, version: Int) -> Unit,
    riddlesResult: SyncStatus<Any>,
    riddlesResultProgress: Float,
    syncTongueTwisters: (total: Int, version: Int) -> Unit,
    tongueTwistersResult: SyncStatus<Any>,
    tongueTwistersResultProgress: Float,
    syncWritings: (total: Int, version: Int) -> Unit,
    writingsResult: SyncStatus<Any>,
    writingsResultProgress: Float,
    setWritingsPreviousPage: (Int) -> Unit,
    setWritingsPreviousCount: (count: Int, total: Int) -> Unit
) {
    SimpleScaffold(
        onBackClick = onBackClick,
        title = "同步数据"
    ) {
        when (datasetResult) {
            is Result.Error -> {
                Toast.makeText(
                    LocalContext.current,
                    "${datasetResult.exception?.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }

            Result.Loading -> {
                LinearProgressIndicator(modifier = modifier.fillMaxWidth())
            }

            is Result.Success -> {
                TipDialog()

                val nameList = datasetResult.data.map { it.name }
                val countList = datasetResult.data.map { it.count }
                val versionList = datasetResult.data.map { it.version }

                val items = listOf(
                    Group(
                        title = "古诗文",
                        items = listOf(
                            Item(
                                title = "经典诗文",
                                name = "classic_poems",
                                localVersion = datasetPref.classicCultureClassicPoemsVersion,
                                status = classicPoemsResult,
                                progress = classicPoemsResultProgress,
                                onClick = { count: Int, version: Int ->
                                    syncClassicPoems(count, version)
                                }
                            ),
                            Item(
                                title = "诗文",
                                name = "writings",
                                localVersion = datasetPref.classicCultureWritingsVersion,
                                status = writingsResult,
                                progress = writingsResultProgress,
                                onClick = { count: Int, version: Int ->
                                    syncWritings(count, version)
                                }
                            ),
                            Item(
                                title = "诗文名句",
                                name = "poem_sentences",
                                localVersion = datasetPref.classicCultureSentencesVersion,
                                status = poemSentencesResult,
                                progress = poemSentencesResultProgress,
                                onClick = { count: Int, version: Int ->
                                    syncPoemSentences(count, version)
                                }
                            ),
                            Item(
                                title = "人物",
                                name = "people",
                                localVersion = datasetPref.classicCulturePeopleVersion,
                                status = peopleResult,
                                progress = peopleResultProgress,
                                onClick = { count: Int, version: Int -> syncPeople(count, version) }
                            )
                        ),
                    ),
                    Group(
                        title = "现代汉语",
                        items = listOf(
                            Item(
                                title = "汉字",
                                name = "dictionary",
                                localVersion = datasetPref.chineseDictionaryVersion,
                                status = dictionaryResult,
                                progress = dictionaryResultProgress,
                                onClick = { count: Int, version: Int ->
                                    syncDictionary(count, version)
                                }
                            ),
                            Item(
                                title = "词语",
                                name = "expressions",
                                localVersion = datasetPref.chineseExpressionVersion,
                                status = chineseExpressionResult,
                                progress = chineseExpressionResultProgress,
                                onClick = { count: Int, version: Int ->
                                    syncChineseExpression(count, version)
                                }
                            ),
                            Item(
                                title = "成语",
                                name = "idioms",
                                localVersion = datasetPref.chineseIdiomsVersion,
                                status = idiomsResult,
                                progress = idiomsResultProgress,
                                onClick = { count: Int, version: Int -> syncIdioms(count, version) }
                            ),
                            Item(
                                title = "歇后语",
                                name = "chinese_wisecracks",
                                localVersion = datasetPref.chineseWisecracksVersion,
                                status = chineseWisecracksResult,
                                progress = chineseWisecracksResultProgress,
                                onClick = { count: Int, version: Int ->
                                    syncChineseWisecracks(count, version)
                                }
                            ),
                            Item(
                                title = "谜语",
                                name = "riddles",
                                localVersion = datasetPref.chineseRiddlesVersion,
                                status = riddlesResult,
                                progress = riddlesResultProgress,
                                onClick = { count: Int, version: Int ->
                                    syncRiddles(count, version)
                                }
                            ),
                            Item(
                                title = "谚语",
                                name = "proverbs",
                                localVersion = datasetPref.chineseProverVersion,
                                status = chineseProverbResult,
                                progress = chineseProverbResultProgress,
                                onClick = { count: Int, version: Int ->
                                    syncChineseProverb(count, version)
                                }
                            ),
                            Item(
                                title = "绕口令",
                                name = "tongue_twisters",
                                localVersion = datasetPref.chineseTongueTwistersVersion,
                                status = tongueTwistersResult,
                                progress = tongueTwistersResultProgress,
                                onClick = { count: Int, version: Int ->
                                    syncTongueTwisters(count, version)
                                }
                            ),
                            Item(
                                title = "对联",
                                name = "chinese_antithetical_couplet",
                                localVersion = datasetPref.chineseAntitheticalCoupletVersion,
                                status = chineseAntitheticalCoupletResult,
                                progress = chineseAntitheticalCoupletProgress,
                                onClick = { count: Int, version: Int ->
                                    syncChineseAntitheticalCouplet(count, version)
                                }
                            ),
                            Item(
                                title = "歌词",
                                name = "lyrics",
                                localVersion = datasetPref.chineseLyricVersion,
                                status = lyricResult,
                                progress = lyricResultProgress,
                                onClick = { count: Int, version: Int ->
                                    syncLyric(count, version)
                                }
                            ),
                            Item(
                                title = "知识卡片",
                                name = "chinese_knowledge",
                                localVersion = datasetPref.chineseKnowledgeVersion,
                                status = chineseKnowledgeResult,
                                progress = chineseKnowledgeResultProgress,
                                onClick = { count: Int, version: Int ->
                                    syncChineseKnowledge(count, version)
                                }
                            ),
                            Item(
                                title = "句子",
                                name = "chinese_quotes",
                                localVersion = datasetPref.chineseQuoteVersion,
                                status = chineseQuotesResult,
                                progress = chineseQuotesResultProgress,
                                onClick = { count: Int, version: Int ->
                                    syncChineseQuotes(count, version)
                                }
                            ),
                            Item(
                                title = "诗歌",
                                name = "chinese_modern_poetry",
                                localVersion = datasetPref.chineseModernPoetryVersion,
                                status = chineseModernPoetryResult,
                                progress = chineseModernPoetryResultProgress,
                                onClick = { count: Int, version: Int ->
                                    syncChineseModernPoetry(count, version)
                                }
                            ),
                        )
                    ),
                    Group(
                        title = "中国",
                        items = listOf(
                            Item(
                                title = "世界文化遗产",
                                name = "china_world_cultural_heritage",
                                localVersion = datasetPref.chinaWorldCulturalHeritageVersion,
                                status = chinaWorldCultureHeritageResult,
                                progress = chinaWorldCultureHeritageProgress,
                                onClick = { count, version ->
                                    syncChinaWorldCultureHeritage(count, version)
                                }
                            )
                        )
                    )
                )

                LaunchedEffect(datasetPref.classicCultureWritingsVersion) {
                    val writingsIndex = nameList.indexOfFirst { it == "writings" }
                    // 诗文续传逻辑,设为上次的进度
                    if (datasetPref.classicCultureWritingsVersion != versionList[writingsIndex]) {
                        if (datasetPref.classicCultureWritingsCurrentCount != countList[writingsIndex]) {
                            setWritingsPreviousPage(datasetPref.classicCultureWritingsCurrentPage)
                            setWritingsPreviousCount(
                                datasetPref.classicCultureWritingsCurrentCount,
                                countList[writingsIndex]
                            )
                        }
                    }
                }

                LazyColumn {
                    items.forEach {
                        item {
                            SettingsTitle(title = it.title)
                        }
                        itemsIndexed(
                            items = it.items
                        ) { _: Int, item: Item ->
                            val key = nameList.indexOf(item.name)

                            if (item.status is SyncStatus.Error) {
                                item.status.exception?.message
                            }

                            Item(
                                title = item.title,
                                subtitle = countList[key].toString(),
                                onClick = { item.onClick(countList[key], versionList[key]) },
                                enabled = item.localVersion != versionList[key] || item.status == SyncStatus.Loading,
                                showProgressIndicator = item.status == SyncStatus.Loading,
                                progress = item.progress,
                                error = if (item.status is SyncStatus.Error) {
                                    item.status.exception?.message
                                } else null
                            )
                        }
                    }
                }
            }
        }
    }
}

private class Group(
    val title: String,
    val items: List<Item>
)

private class Item(
    val title: String,
    val name: String,
    val localVersion: Int,
    val status: SyncStatus<Any>,
    val progress: Float,
    val onClick: (count: Int, version: Int) -> Unit,
)

@Composable
private fun TipDialog() {
    var showDialog by rememberSaveable { mutableStateOf(true) }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                Button(onClick = { showDialog = false }) {
                    Text(text = "知道了")
                }
            },
            title = {
                Text(text = "提示")
            },
            text = {
                Text(text = "数据量大，预计需要半小时以上时间，建议在电量充足、流量充足的空闲时间同步数据！建议一个一个同步,不然可能因占用太多内存而被系统杀死进程。")
            }
        )
    }
}

@SuppressLint("DefaultLocale")
@Composable
private fun Item(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    onClick: () -> Unit,
    enabled: Boolean,
    showProgressIndicator: Boolean,
    progress: Float,
    error: String?,
) {
    Column(
        modifier = modifier.padding(16.dp, 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(text = title)
                Text(
                    text = subtitle,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = .5f),
                    style = MaterialTheme.typography.labelMedium
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (progress > 0 && progress != 100f) {
                    Text(text = "${String.format("%.2f", progress * 100)}%")
                }

                if (enabled) {
                    Icon(
                        imageVector = Icons.Default.ArrowCircleDown,
                        contentDescription = null,
                        tint = Color.Red
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.CheckCircleOutline,
                        contentDescription = null,
                        tint = Color.Green
                    )
                }

                IconButton(
                    onClick = onClick,
                ) {
                    if (showProgressIndicator) {
                        CircularProgressIndicator()
                    }
                    Icon(imageVector = Icons.Default.Download, contentDescription = null)
                }
            }
        }

        if (error != null) {
            Text(text = error, color = MaterialTheme.colorScheme.error)
        }
    }
}