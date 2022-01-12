/*
 Navicat Premium Data Transfer

 Source Server         : ihave.top
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : ihave.top:3306
 Source Schema         : top_app

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 12/01/2022 14:47:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for top_app
-- ----------------------------
DROP TABLE IF EXISTS `top_app`;
CREATE TABLE `top_app` (
  `id` bigint NOT NULL COMMENT 'id',
  `app_name` varchar(45) DEFAULT NULL COMMENT 'app名称',
  `privacy_agreement` longtext COMMENT '隐私协议',
  `user_agreement` longtext COMMENT '用户协议',
  `android_sdk` longtext COMMENT '安卓三方SDK列表',
  `android_use` longtext COMMENT '安卓使用权限',
  `ios_sdk` longtext COMMENT 'IOS三方SDK列表',
  `ios_use` longtext COMMENT 'IOS使用权限',
  `status` int DEFAULT '0' COMMENT '状态',
  `is_deleted` int DEFAULT '0' COMMENT '0未删，1删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='app表';

-- ----------------------------
-- Records of top_app
-- ----------------------------
BEGIN;
INSERT INTO `top_app` VALUES (1460503343033094146, 'app', '<p class=\"ql-align-justify\">&nbsp;</p><p class=\"ql-align-center\"><strong>「开拍」服务协议</strong></p><p class=\"ql-align-justify\">&nbsp;</p><p class=\"ql-align-justify\">【说明】</p><p class=\"ql-align-justify\">欢迎您使用「开拍」移动应用程序（以下简称“<strong>本服务</strong>”）！本服务是深圳市商汤科技有限公司及其关联方（以下简称“<strong>商汤</strong>”或“<strong>我们</strong>”）运营的一款视频智能剪辑移动应用。</p><p class=\"ql-align-justify\">为使用本服务，您应当阅读并遵守《「开拍」服务协议》（以下称“<strong>本协议</strong>”）、《「开拍」个人信息保护政策》等相关协议、政策及规则。<strong>请您务必审慎阅读、充分理解本协议及其他相关协议、政策和规则的各条款内容。免除或者限制责任的条款将以粗体标识，用户需要重点阅读。您点击“同意”或“下一步”，或您使用本服务，或者以其他任何明示或者默示方式表示接受本协议的，均视为您已阅读并同意签署本协议。</strong></p><p class=\"ql-align-justify\">如果您因年龄、智力等因素而不具有完全民事行为能力，请在法定监护人（以下简称“<strong>监护人</strong>”）的陪同下阅读并判断是否同意本协议，并特别注意未成年人使用条款。<strong>如您未经监护人同意使用我们的服务的，则您及您的监护人应依法承担因此导致的一切后果。</strong></p><p class=\"ql-align-justify\">如果您是中国大陆地区以外的用户，您订立或履行本协议还需要同时遵守您所属和/或所处国家或地区的法律。</p><p class=\"ql-align-justify\">本协议签订地为中华人民共和国广东省深圳市。</p><p class=\"ql-align-justify\">&nbsp;</p><p class=\"ql-align-justify\"><strong>一、协议范围</strong></p><p class=\"ql-align-justify\">本协议双方为深圳市商汤科技有限公司及其关联方（下称<strong>“商汤”</strong>或<strong>“我们”</strong>）与「开拍」网络用户（以下简称<strong>“用户”</strong>或<strong>“您”</strong>），本协议具有合同效力。</p><p class=\"ql-align-justify\"><strong>用户在使用本服务时，承诺接受并遵守本协议。商汤有权根据需要不时地制定、修改本协议，如本协议有任何变更，商汤将在应用上更新并以显著方式通知用户。如用户不同意相关变更，请停止使用本服务。经修订的协议一经在应用公布后，立即自动生效。用户继续使用本服务将被视为已接受了修改后的协议。如有任何问题，您可向商汤咨询。</strong></p><p class=\"ql-align-justify\"><strong>尽管有上述约定，如果您是通过合法渠道购买的本服务，商汤承诺，除非本协议另有约定或我们与您另行达成协议，本协议的修订不会减损您在购买本服务时所享有的权利和义务。</strong></p><p class=\"ql-align-justify\">&nbsp;</p><p class=\"ql-align-justify\"><strong>二、帐号注册与管理</strong></p><p class=\"ql-align-justify\">1.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户可以在应用上通过手机号进行帐户注册，并按要求填写相关信息并确认同意本协议、《「开拍」个人信息保护政策》等相关协议、政策及规则。</p><p class=\"ql-align-justify\">2.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;在您使用帐号时，您须承诺和保证：</p><p class=\"ql-align-justify\">（1）&nbsp;&nbsp;&nbsp;&nbsp;对注册信息的真实性、合法性、有效性承担全部责任，及时更新注册信息，不得冒充他人，不得利用他人的名义使用本服务；</p><p class=\"ql-align-justify\">（2）&nbsp;&nbsp;&nbsp;&nbsp;妥善保管帐号及密码，若用户帐号或帐号下行为出现纠纷时，由用户承担全部责任。所述帐号下行为包括但不限于，通过应用控制设备进行的任何行为以及由此产生的任何结果；</p><p class=\"ql-align-justify\">（3）&nbsp;&nbsp;&nbsp;&nbsp;用户因进行交易、获取有偿服务或接触「开拍」服务器而发生的所有应纳税赋，以及一切硬件、软件、服务及其它方面的费用均由用户承担。</p><p class=\"ql-align-justify\">3.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户注册帐号后，为符合相关法律法规的要求，保障您的帐号安全，商汤可能会定期或不定期采用不同的方式对您的身份进行验证，包括但不限于要求输入账号密码、手机号验证码等。<strong>如商汤发现用户以虚假信息骗取帐号名称注册，商汤有权不经通知单方采取限期改正、暂停使用、终止帐号等措施。</strong></p><p class=\"ql-align-justify\">4.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;一个帐号只能由一个用户使用，不允许多个用户使用同一个帐号，如果用户违反此规定，商汤有权将此帐号作为无效处理。</p><p class=\"ql-align-justify\"><strong>5.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</strong>用户发现任何非法使用用户帐号的情况，应立即通知商汤，商汤将给予最大限度地配合和处理。<strong>但对于用户因帐号或密码泄露造成的各种损失，商汤不承担任何责任。</strong></p><p class=\"ql-align-justify\">6.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;如用户向商汤提出注销帐户，经商汤审核同意，由商汤注销该注册用户，用户即解除与商汤的服务协议关系。但注销该用户帐号后，商汤仍保留下列权利：</p><p class=\"ql-align-justify\"><strong>（1）&nbsp;&nbsp;&nbsp;&nbsp;商汤有权保留该用户的注册数据及以前的行为记录，但属于个人信息的除外；</strong></p><p class=\"ql-align-justify\"><strong>（2）&nbsp;&nbsp;&nbsp;&nbsp;如用户在注销前在使用本服务过程中存在违法行为或违反合同的行为，商汤仍可行使本协议所规定的权利。</strong></p><p class=\"ql-align-justify\">&nbsp;</p><p class=\"ql-align-justify\"><strong>三、用户权利和义务：</strong></p><p class=\"ql-align-justify\">用户享有合法使用本服务的权利，但应当遵守以下规则。</p><p class=\"ql-align-justify\">1.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;遵守所有适用的法律和法规，包括但不限于有关互联网使用的法律和法规。</p><p class=\"ql-align-justify\">2.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;遵守本协议、《「开拍」个人信息保护政策》等相关协议、政策及规则的约定。</p><p class=\"ql-align-justify\"><strong>3.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;不得利用本服务制作、复制、查阅、传播和存储下列信息：</strong></p><p class=\"ql-align-justify\"><strong>（1）&nbsp;&nbsp;&nbsp;&nbsp;破坏宪法所确立的基本原则的信息；</strong></p><p class=\"ql-align-justify\"><strong>（2）&nbsp;&nbsp;&nbsp;&nbsp;危害国家安全、泄露国家秘密、颠覆国家政权、破坏国家统一的信息；</strong></p><p class=\"ql-align-justify\"><strong>（3）&nbsp;&nbsp;&nbsp;&nbsp;损害国家荣誉和利益的信息；</strong></p><p class=\"ql-align-justify\"><strong>（4）&nbsp;&nbsp;&nbsp;&nbsp;煽动民族仇恨、民族歧视，破坏民族团结的信息；</strong></p><p class=\"ql-align-justify\"><strong>（5）&nbsp;&nbsp;&nbsp;&nbsp;破坏国家宗教政策，宣扬邪教和封建迷信的信息；</strong></p><p class=\"ql-align-justify\"><strong>（6）&nbsp;&nbsp;&nbsp;&nbsp;谣言、扰乱社会秩序，破坏社会稳定的信息；</strong></p><p class=\"ql-align-justify\"><strong>（7）&nbsp;&nbsp;&nbsp;&nbsp;淫秽、色情、赌博、暴力、凶杀、恐怖或者教唆犯罪的信息；</strong></p><p class=\"ql-align-justify\"><strong>（8）&nbsp;&nbsp;&nbsp;&nbsp;侮辱或者诽谤他人，侵害他人合法权益的信息；</strong></p><p class=\"ql-align-justify\"><strong>（9）&nbsp;&nbsp;&nbsp;&nbsp;损害「开拍」及商汤集团声誉和商业利益的信息；</strong></p><p class=\"ql-align-justify\"><strong>（10）&nbsp;其他任何不符合国家法律、法规规定的资料、信息；</strong></p><p class=\"ql-align-justify\"><strong>4.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;不得利用本服务从事下列行为</strong></p><p class=\"ql-align-justify\"><strong>（1）&nbsp;&nbsp;&nbsp;&nbsp;对本服务进行任何形式的许可、出售、租赁、转让、发行或做其他商业用途，除非您与我们另有约定；</strong></p><p class=\"ql-align-justify\"><strong>（2）&nbsp;&nbsp;&nbsp;&nbsp;未经许可非法进入他人的设备，干扰其他用户的正常使用；</strong></p><p class=\"ql-align-justify\"><strong>（3）&nbsp;&nbsp;&nbsp;&nbsp;故意制作、传播计算机病毒等破坏性程序、电脑病毒、蠕虫、恶意代码，干扰或侵犯本服务的正常运行；</strong></p><p class=\"ql-align-justify\">（4）&nbsp;&nbsp;&nbsp;&nbsp;<strong>其他任何不符合国家法律、法规规定的行为。</strong></p><p class=\"ql-align-justify\"><br></p><p class=\"ql-align-justify\"><strong>四、商汤的权利和义务：</strong></p><p class=\"ql-align-justify\">1.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;商汤将极力保障在现有技术上维护整个「开拍」的正常运行，并努力提升和改进技术，使用户的使用活动得以顺利进行；商汤将本着诚实信用的原则向用户提供相关产品和服务，不得随意中断或停止提供该项服务。<strong>但由于不可抗力或者其它非人为因素造成的服务的中断或停止，商汤不承担任何责任。</strong></p><p class=\"ql-align-justify\">2.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;对于用户在注册使用本服务中所遇到的与交易或注册有关的问题及反映的情况，商汤将及时作出回复；</p><p class=\"ql-align-justify\">3.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;商汤享有对用户网上活动的监督和指导权，商汤会对用户是否涉嫌违反使用规范做出认定，并根据认定结果中止、终止对用户的使用许可或采取其他依本约定可采取的限制措施。此外，商汤有权依据相关法律法规追究用户的法律责任。<strong>请您知悉，尽管有前述约定，商汤不会使用您通过硬件收集的图像数据对您的使用行为进行监测。</strong></p><p class=\"ql-align-justify\"><strong><em>&nbsp;</em></strong></p><p class=\"ql-align-justify\"><strong>五、知识产权</strong></p><p class=\"ql-align-justify\">商汤是「开拍」移动应用程序的知识产权权利人。本服务的一切著作权、商标权、专利权、商业秘密等知识产权，以及与本服务相关的所有信息内容（包括但不限于文字、图片、音频、视频、图表、界面设计、版面框架、有关数据或电子文档等）均受当地法律法规和相应的国际条约保护，商汤享有上述知识产权。<strong>为避免疑义，用户独立使用硬件记录或生成的内容，其合法权利均由用户本人享有。</strong></p><p class=\"ql-align-justify\"><strong>对于商汤享有知识产权的信息内容，未经商汤的同意，用户不得在任何媒体直接或间接发布、播放、出于播放或发布目的而改写或再发行，或者用于其他任何商业目的。不得擅自对本服务中包含的任何资料（包括但不限于文本、图片、图形、音频、视频、API文档等）进行任何形式的翻录、复制或从事其他任何违反相关法律、法规的活动。针对上述侵犯知识产权和/或其它财产所有权的个人和组织，商汤和相关权利人将依法追究其民事或刑事责任。</strong></p><p class=\"ql-align-justify\"><strong>六、服务协议的修改和终止</strong></p><p class=\"ql-align-justify\"><strong>1.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;商汤将根据互联网的发展和法律、法规的变化，在认为必要时单方面修改服务协议。</strong>如本协议有任何变更，商汤将在应用上更新并以显著方式通知用户。如用户不同意相关变更，请停止使用本服务。经修订的协议一经在应用公布后，立即自动生效。用户继续使用本服务将被视为已接受了修改后的协议。</p><p class=\"ql-align-justify\">2.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>本协议自您接受之日起生效，在您使用本服务的过程中持续有效，直至依据本协议终止。一旦本协议终止，您使用帐户和本服务的权利即告终止。您应当知晓这意味着您的用户内容将从我们的数据库中删除。商汤不因终止本协议对您承担任何责任，包括终止您的帐户和删除您的内容。</strong></p><p class=\"ql-align-justify\">&nbsp;</p><p class=\"ql-align-justify\"><strong>七、损害赔偿</strong></p><p class=\"ql-align-justify\">用户明确理解并同意，如因其违反有关法律或者本协议之规定，使商汤遭受任何损失，受到任何第三方的索赔，或任何行政管理部门的处罚，商汤有权向用户进行追偿，包括合理必要的律师费用、差旅费用、诉讼费、仲裁费、赔偿金、和解金、调解金、罚金等维权费用。</p><p class=\"ql-align-justify\">您已同意，除非获得商汤书面同意，您不得在您与商汤共同对第三方提起的诉讼中单方和解。</p><p class=\"ql-align-justify\">在任何情况下，商汤都不对您或任何第三方因本协议产生的任何间接性、后果性、惩戒性的、偶然的、特殊或惩罚性的损害赔偿承担责任。</p><p class=\"ql-align-justify\">&nbsp;</p><p class=\"ql-align-justify\"><strong>八、责任范围及免责声明：</strong></p><p class=\"ql-align-justify\"><strong>1.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</strong>维护我们提供的服务的安全与正常使用是我们和您的共同责任，我们将按照行业标准合理审慎地采取必要技术措施保护您的终端设备信息和数据安全，<strong>但是您理解和同意由于网络环境并非完全没有风险，我们并不能就此提供完全保证。用户应当自己承担系统受损或资料丢失的风险和责任。</strong></p><p class=\"ql-align-justify\"><strong>2.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您明确理解和同意，商汤不对因下述任一情况而导致的任何损害赔偿承担责任，包括但不限于利润、商誉、使用、数据等方面的损失或其它无形损失的损害赔偿(无论商汤是否已被告知该等损害赔偿的可能性)：</strong></p><p class=\"ql-align-justify\"><strong>（1）&nbsp;&nbsp;&nbsp;&nbsp;第三方对本服务的行为，包括但不限于计算机病毒、木马或其他恶意程序、黑客攻击、电脑软件系统硬件和通信线路的故障等，或非因商汤的原因而引起的与本服务有关的任何其它事宜；</strong></p><p class=\"ql-align-justify\"><strong>（2）&nbsp;&nbsp;&nbsp;&nbsp;不可抗力原因造成的风险因素使得本服务受到影响。该等因第三方原因或不可抗力而造成的风险因素是指不能预见、不能克服并不能避免且对一方或双方造成重大影响的客观事件，包括但不限于信息网络设备维护、信息网络连接故障、电力故障、洪水、地震、瘟疫流行、风暴、战争、动乱、生产力或生产资料不足、罢工、政府行为、法律法规变动、司法行政机关的命令、其他不可抗力或第三方的不作为而造成的不能服务或延迟服务等。</strong></p><p class=\"ql-align-justify\">3.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户知晓并同意，由于商汤并未向用户收取APP相关的服务费用，因此商汤可能基于国家政策因素、市场因素、商汤自身业务调整等因素影响，在发出通知或不发出通知的情况下，自行决定随时终止APP相关的服务或其任何部分，并删除用户在APP中的任何资料。</p><p class=\"ql-align-justify\">&nbsp;</p><p class=\"ql-align-justify\"><strong>九、个人信息保护政策：</strong></p><p class=\"ql-align-justify\">我们非常重视和保护您的个人信息，制定了专门的《「开拍」个人信息保护政策》等相关协议、政策及规则。在您使用我们提供的产品和服务前，请您务必进行阅读。</p><p class=\"ql-align-justify\">&nbsp;</p><p class=\"ql-align-justify\"><strong>十、法律与管辖：</strong></p><p class=\"ql-align-justify\">本协议根据现行中华人民共和国法律法规制定。如发生协议条款与中华人民共和国法律法规相抵触时，则这些条款将完全按法律法规的规定重新解释，本协议的其它条款仍对商汤和用户具有法律约束力。因本协议而发生的任何争议应向本协议签订地有管辖权的人民法院提起诉讼。</p><p class=\"ql-align-justify\"><br></p>', '<body style=\"background-color: #000000;color: #ffffff;margin: 0;padding: 2px;\"><div style=\"padding: 15px;\"><p class=\"ql-align-justify\">&nbsp;</p><p class=\"ql-align-center\"><strong>「开拍」服务协议</strong></p><p class=\"ql-align-justify\">&nbsp;</p><p class=\"ql-align-justify\">【说明】</p><p class=\"ql-align-justify\">欢迎您使用「开拍」移动应用程序（以下简称“<strong>本服务</strong>”）！本服务是深圳市商汤科技有限公司及其关联方（以下简称“<strong>商汤</strong>”或“<strong>我们</strong>”）运营的一款视频智能剪辑移动应用。</p><p class=\"ql-align-justify\">为使用本服务，您应当阅读并遵守《「开拍」服务协议》（以下称“<strong>本协议</strong>”）、《「开拍」个人信息保护政策》等相关协议、政策及规则。<strong>请您务必审慎阅读、充分理解本协议及其他相关协议、政策和规则的各条款内容。免除或者限制责任的条款将以粗体标识，用户需要重点阅读。您点击“同意”或“下一步”，或您使用本服务，或者以其他任何明示或者默示方式表示接受本协议的，均视为您已阅读并同意签署本协议。</strong></p><p class=\"ql-align-justify\">如果您因年龄、智力等因素而不具有完全民事行为能力，请在法定监护人（以下简称“<strong>监护人</strong>”）的陪同下阅读并判断是否同意本协议，并特别注意未成年人使用条款。<strong>如您未经监护人同意使用我们的服务的，则您及您的监护人应依法承担因此导致的一切后果。</strong></p><p class=\"ql-align-justify\">如果您是中国大陆地区以外的用户，您订立或履行本协议还需要同时遵守您所属和/或所处国家或地区的法律。</p><p class=\"ql-align-justify\">本协议签订地为中华人民共和国广东省深圳市。</p><p class=\"ql-align-justify\">&nbsp;</p><p class=\"ql-align-justify\"><strong>一、协议范围</strong></p><p class=\"ql-align-justify\">本协议双方为深圳市商汤科技有限公司及其关联方（下称<strong>“商汤”</strong>或<strong>“我们”</strong>）与「开拍」网络用户（以下简称<strong>“用户”</strong>或<strong>“您”</strong>），本协议具有合同效力。</p><p class=\"ql-align-justify\"><strong>用户在使用本服务时，承诺接受并遵守本协议。商汤有权根据需要不时地制定、修改本协议，如本协议有任何变更，商汤将在应用上更新并以显著方式通知用户。如用户不同意相关变更，请停止使用本服务。经修订的协议一经在应用公布后，立即自动生效。用户继续使用本服务将被视为已接受了修改后的协议。如有任何问题，您可向商汤咨询。</strong></p><p class=\"ql-align-justify\"><strong>尽管有上述约定，如果您是通过合法渠道购买的本服务，商汤承诺，除非本协议另有约定或我们与您另行达成协议，本协议的修订不会减损您在购买本服务时所享有的权利和义务。</strong></p><p class=\"ql-align-justify\">&nbsp;</p><p class=\"ql-align-justify\"><strong>二、帐号注册与管理</strong></p><p class=\"ql-align-justify\">1.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户可以在应用上通过手机号进行帐户注册，并按要求填写相关信息并确认同意本协议、《「开拍」个人信息保护政策》等相关协议、政策及规则。</p><p class=\"ql-align-justify\">2.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;在您使用帐号时，您须承诺和保证：</p><p class=\"ql-align-justify\">（1）&nbsp;&nbsp;&nbsp;&nbsp;对注册信息的真实性、合法性、有效性承担全部责任，及时更新注册信息，不得冒充他人，不得利用他人的名义使用本服务；</p><p class=\"ql-align-justify\">（2）&nbsp;&nbsp;&nbsp;&nbsp;妥善保管帐号及密码，若用户帐号或帐号下行为出现纠纷时，由用户承担全部责任。所述帐号下行为包括但不限于，通过应用控制设备进行的任何行为以及由此产生的任何结果；</p><p class=\"ql-align-justify\">（3）&nbsp;&nbsp;&nbsp;&nbsp;用户因进行交易、获取有偿服务或接触「开拍」服务器而发生的所有应纳税赋，以及一切硬件、软件、服务及其它方面的费用均由用户承担。</p><p class=\"ql-align-justify\">3.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户注册帐号后，为符合相关法律法规的要求，保障您的帐号安全，商汤可能会定期或不定期采用不同的方式对您的身份进行验证，包括但不限于要求输入账号密码、手机号验证码等<a href=\"http://120.55.53.149:33001/#_msocom_1\" rel=\"noopener noreferrer\" target=\"_blank\">[z1]</a>&nbsp;<a href=\"http://120.55.53.149:33001/#_msocom_2\" rel=\"noopener noreferrer\" target=\"_blank\">[l2]</a>&nbsp;。<strong>如商汤发现用户以虚假信息骗取帐号名称注册，商汤有权不经通知单方采取限期改正、暂停使用、终止帐号等措施。</strong></p><p class=\"ql-align-justify\">4.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;一个帐号只能由一个用户使用，不允许多个用户使用同一个帐号，如果用户违反此规定，商汤有权将此帐号作为无效处理。</p><p class=\"ql-align-justify\"><strong>5.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</strong>用户发现任何非法使用用户帐号的情况，应立即通知商汤，商汤将给予最大限度地配合和处理。<strong>但对于用户因帐号或密码泄露造成的各种损失，商汤不承担任何责任。</strong></p><p class=\"ql-align-justify\">6.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;如用户向商汤提出注销帐户，经商汤审核同意，由商汤注销该注册用户，用户即解除与商汤的服务协议关系。但注销该用户帐号后，商汤仍保留下列权利：</p><p class=\"ql-align-justify\"><strong>（1）&nbsp;&nbsp;&nbsp;&nbsp;商汤有权保留该用户的注册数据及以前的行为记录，但属于个人信息的除外；</strong></p><p class=\"ql-align-justify\"><strong>（2）&nbsp;&nbsp;&nbsp;&nbsp;如用户在注销前在使用本服务过程中存在违法行为或违反合同的行为，商汤仍可行使本协议所规定的权利。</strong></p><p class=\"ql-align-justify\">&nbsp;</p><p class=\"ql-align-justify\"><strong>三、用户权利和义务：</strong></p><p class=\"ql-align-justify\">用户享有合法使用本服务的权利，但应当遵守以下规则。</p><p class=\"ql-align-justify\">1.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;遵守所有适用的法律和法规，包括但不限于有关互联网使用的法律和法规。</p><p class=\"ql-align-justify\">2.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;遵守本协议、《「开拍」个人信息保护政策》等相关协议、政策及规则的约定。</p><p class=\"ql-align-justify\"><strong>3.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;不得利用本服务制作、复制、查阅、传播和存储下列信息：</strong></p><p class=\"ql-align-justify\"><strong>（1）&nbsp;&nbsp;&nbsp;&nbsp;破坏宪法所确立的基本原则的信息；</strong></p><p class=\"ql-align-justify\"><strong>（2）&nbsp;&nbsp;&nbsp;&nbsp;危害国家安全、泄露国家秘密、颠覆国家政权、破坏国家统一的信息；</strong></p><p class=\"ql-align-justify\"><strong>（3）&nbsp;&nbsp;&nbsp;&nbsp;损害国家荣誉和利益的信息；</strong></p><p class=\"ql-align-justify\"><strong>（4）&nbsp;&nbsp;&nbsp;&nbsp;煽动民族仇恨、民族歧视，破坏民族团结的信息；</strong></p><p class=\"ql-align-justify\"><strong>（5）&nbsp;&nbsp;&nbsp;&nbsp;破坏国家宗教政策，宣扬邪教和封建迷信的信息；</strong></p><p class=\"ql-align-justify\"><strong>（6）&nbsp;&nbsp;&nbsp;&nbsp;谣言、扰乱社会秩序，破坏社会稳定的信息；</strong></p><p class=\"ql-align-justify\"><strong>（7）&nbsp;&nbsp;&nbsp;&nbsp;淫秽、色情、赌博、暴力、凶杀、恐怖或者教唆犯罪的信息；</strong></p><p class=\"ql-align-justify\"><strong>（8）&nbsp;&nbsp;&nbsp;&nbsp;侮辱或者诽谤他人，侵害他人合法权益的信息；</strong></p><p class=\"ql-align-justify\"><strong>（9）&nbsp;&nbsp;&nbsp;&nbsp;损害「开拍」及商汤集团声誉和商业利益的信息；</strong></p><p class=\"ql-align-justify\"><strong>（10）&nbsp;其他任何不符合国家法律、法规规定的资料、信息；</strong></p><p class=\"ql-align-justify\"><strong>4.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;不得利用本服务从事下列行为</strong></p><p class=\"ql-align-justify\"><strong>（1）&nbsp;&nbsp;&nbsp;&nbsp;对本服务进行任何形式的许可、出售、租赁、转让、发行或做其他商业用途，除非您与我们另有约定；</strong></p><p class=\"ql-align-justify\"><strong>（2）&nbsp;&nbsp;&nbsp;&nbsp;未经许可非法进入他人的设备，干扰其他用户的正常使用；</strong></p><p class=\"ql-align-justify\"><strong>（3）&nbsp;&nbsp;&nbsp;&nbsp;故意制作、传播计算机病毒等破坏性程序、电脑病毒、蠕虫、恶意代码，干扰或侵犯本服务的正常运行；</strong></p><p class=\"ql-align-justify\">（4）&nbsp;&nbsp;&nbsp;&nbsp;<strong>其他任何不符合国家法律、法规规定的行为。</strong></p><p class=\"ql-align-justify\"><br></p><p class=\"ql-align-justify\"><strong>四、商汤的权利和义务：</strong></p><p class=\"ql-align-justify\">1.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;商汤将极力保障在现有技术上维护整个「开拍」的正常运行，并努力提升和改进技术，使用户的使用活动得以顺利进行；商汤将本着诚实信用的原则向用户提供相关产品和服务，不得随意中断或停止提供该项服务。<strong>但由于不可抗力或者其它非人为因素造成的服务的中断或停止，商汤不承担任何责任。</strong></p><p class=\"ql-align-justify\">2.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;对于用户在注册使用本服务中所遇到的与交易或注册有关的问题及反映的情况，商汤将及时作出回复；</p><p class=\"ql-align-justify\">3.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;商汤享有对用户网上活动的监督和指导权，商汤会对用户是否涉嫌违反使用规范做出认定，并根据认定结果中止、终止对用户的使用许可或采取其他依本约定可采取的限制措施。此外，商汤有权依据相关法律法规追究用户的法律责任。<strong>请您知悉，尽管有前述约定，商汤不会使用您通过硬件收集的图像数据对您的使用行为进行监测。</strong></p><p class=\"ql-align-justify\"><strong><em>&nbsp;</em></strong></p><p class=\"ql-align-justify\"><strong>五、知识产权</strong></p><p class=\"ql-align-justify\">商汤是「开拍」移动应用程序的知识产权权利人。本服务的一切著作权、商标权、专利权、商业秘密等知识产权，以及与本服务相关的所有信息内容（包括但不限于文字、图片、音频、视频、图表、界面设计、版面框架、有关数据或电子文档等）均受当地法律法规和相应的国际条约保护，商汤享有上述知识产权。<strong>为避免疑义，用户独立使用硬件记录或生成的内容，其合法权利均由用户本人享有。</strong></p><p class=\"ql-align-justify\"><strong>对于商汤享有知识产权的信息内容，未经商汤的同意，用户不得在任何媒体直接或间接发布、播放、出于播放或发布目的而改写或再发行，或者用于其他任何商业目的。不得擅自对本服务中包含的任何资料（包括但不限于文本、图片、图形、音频、视频、API文档等）进行任何形式的翻录、复制或从事其他任何违反相关法律、法规的活动。针对上述侵犯知识产权和/或其它财产所有权的个人和组织，商汤和相关权利人将依法追究其民事或刑事责任。</strong></p><p class=\"ql-align-justify\"><strong>六、服务协议的修改和终止</strong></p><p class=\"ql-align-justify\"><strong>1.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;商汤将根据互联网的发展和法律、法规的变化，在认为必要时单方面修改服务协议。</strong>如本协议有任何变更，商汤将在应用上更新并以显著方式通知用户。如用户不同意相关变更，请停止使用本服务。经修订的协议一经在应用公布后，立即自动生效。用户继续使用本服务将被视为已接受了修改后的协议。</p><p class=\"ql-align-justify\">2.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>本协议自您接受之日起生效，在您使用本服务的过程中持续有效，直至依据本协议终止。一旦本协议终止，您使用帐户和本服务的权利即告终止。您应当知晓这意味着您的用户内容将从我们的数据库中删除。商汤不因终止本协议对您承担任何责任，包括终止您的帐户和删除您的内容。</strong></p><p class=\"ql-align-justify\">&nbsp;</p><p class=\"ql-align-justify\"><strong>七、损害赔偿</strong></p><p class=\"ql-align-justify\">用户明确理解并同意，如因其违反有关法律或者本协议之规定，使商汤遭受任何损失，受到任何第三方的索赔，或任何行政管理部门的处罚，商汤有权向用户进行追偿，包括合理必要的律师费用、差旅费用、诉讼费、仲裁费、赔偿金、和解金、调解金、罚金等维权费用。</p><p class=\"ql-align-justify\">您已同意，除非获得商汤书面同意，您不得在您与商汤共同对第三方提起的诉讼中单方和解。</p><p class=\"ql-align-justify\">在任何情况下，商汤都不对您或任何第三方因本协议产生的任何间接性、后果性、惩戒性的、偶然的、特殊或惩罚性的损害赔偿承担责任。</p><p class=\"ql-align-justify\">&nbsp;</p><p class=\"ql-align-justify\"><strong>八、责任范围及免责声明：</strong></p><p class=\"ql-align-justify\"><strong>1.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</strong>维护我们提供的服务的安全与正常使用是我们和您的共同责任，我们将按照行业标准合理审慎地采取必要技术措施保护您的终端设备信息和数据安全，<strong>但是您理解和同意由于网络环境并非完全没有风险，我们并不能就此提供完全保证。用户应当自己承担系统受损或资料丢失的风险和责任。</strong></p><p class=\"ql-align-justify\"><strong>2.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您明确理解和同意，商汤不对因下述任一情况而导致的任何损害赔偿承担责任，包括但不限于利润、商誉、使用、数据等方面的损失或其它无形损失的损害赔偿(无论商汤是否已被告知该等损害赔偿的可能性)：</strong></p><p class=\"ql-align-justify\"><strong>（1）&nbsp;&nbsp;&nbsp;&nbsp;第三方对本服务的行为，包括但不限于计算机病毒、木马或其他恶意程序、黑客攻击、电脑软件系统硬件和通信线路的故障等，或非因商汤的原因而引起的与本服务有关的任何其它事宜；</strong></p><p class=\"ql-align-justify\"><strong>（2）&nbsp;&nbsp;&nbsp;&nbsp;不可抗力原因造成的风险因素使得本服务受到影响。该等因第三方原因或不可抗力而造成的风险因素是指不能预见、不能克服并不能避免且对一方或双方造成重大影响的客观事件，包括但不限于信息网络设备维护、信息网络连接故障、电力故障、洪水、地震、瘟疫流行、风暴、战争、动乱、生产力或生产资料不足、罢工、政府行为、法律法规变动、司法行政机关的命令、其他不可抗力或第三方的不作为而造成的不能服务或延迟服务等。</strong></p><p class=\"ql-align-justify\">3.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户知晓并同意，由于商汤并未向用户收取APP相关的服务费用，因此商汤可能基于国家政策因素、市场因素、商汤自身业务调整等因素影响，在发出通知或不发出通知的情况下，自行决定随时终止APP相关的服务或其任何部分，并删除用户在APP中的任何资料。</p><p class=\"ql-align-justify\">&nbsp;</p><p class=\"ql-align-justify\"><strong>九、个人信息保护政策：</strong></p><p class=\"ql-align-justify\">我们非常重视和保护您的个人信息，制定了专门的《「开拍」个人信息保护政策》等相关协议、政策及规则。在您使用我们提供的产品和服务前，请您务必进行阅读。</p><p class=\"ql-align-justify\">&nbsp;</p><p class=\"ql-align-justify\"><strong>十、法律与管辖：</strong></p><p class=\"ql-align-justify\">本协议根据现行中华人民共和国法律法规制定。如发生协议条款与中华人民共和国法律法规相抵触时，则这些条款将完全按法律法规的规定重新解释，本协议的其它条款仍对商汤和用户具有法律约束力。因本协议而发生的任何争议应向本协议签订地有管辖权的人民法院提起诉讼。</p><p class=\"ql-align-justify\"><br></p><p><br></p></div></body>', NULL, NULL, NULL, NULL, 0, 0, '2021-11-16 07:01:46', '2021-12-29 16:02:53');
COMMIT;

-- ----------------------------
-- Table structure for top_auto_code
-- ----------------------------
DROP TABLE IF EXISTS `top_auto_code`;
CREATE TABLE `top_auto_code` (
  `id` bigint NOT NULL COMMENT 'id',
  `table_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '数据库表名',
  `model_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '模块名',
  `gateway_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'api网关名称',
  `vue_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '前端模块名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of top_auto_code
-- ----------------------------
BEGIN;
INSERT INTO `top_auto_code` VALUES (1481131552619421698, 'top_gateway_routes', 'test', 'system', 'system', '2022-01-12 05:10:55');
INSERT INTO `top_auto_code` VALUES (1481150163761283074, 'top_config', 'test', 'system', 'system', '2022-01-12 06:24:52');
COMMIT;

-- ----------------------------
-- Table structure for top_config
-- ----------------------------
DROP TABLE IF EXISTS `top_config`;
CREATE TABLE `top_config` (
  `id` bigint NOT NULL COMMENT '主键',
  `type` varchar(64) DEFAULT NULL COMMENT '配置规则类型',
  `code` varchar(50) NOT NULL COMMENT '配置规则代码',
  `name` varchar(100) NOT NULL COMMENT '配置规则名称',
  `desc` varchar(255) DEFAULT NULL COMMENT '配置规则描述',
  `value` varchar(255) NOT NULL COMMENT '配置值',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='平台配置信息';

-- ----------------------------
-- Records of top_config
-- ----------------------------
BEGIN;
INSERT INTO `top_config` VALUES (1423172971438190594, 'SMS', 'SIGN', '短信签名', '签名为top', 'top', '2021-08-05 06:44:12');
INSERT INTO `top_config` VALUES (1423174062749954049, 'SMS', 'SMS_LOGIN_MESSAGE', '短信模板的id', '您好，动态验证码：${VerificationCode}，请妥善保管。（该验证码5分钟内有效）。', '123456', '2021-08-05 06:48:33');
INSERT INTO `top_config` VALUES (1423174536492396545, 'ROLE', 'DEFAULT_ROLE_NAME', '默认角色', '普通用户', 'ordinary', '2021-08-05 06:50:25');
INSERT INTO `top_config` VALUES (1425708256504041473, 'RESOURCE', 'DEFAULT_RESOURCE_ROLE', '资源', '默认资源角色', 'ordinary', '2021-08-12 06:38:31');
INSERT INTO `top_config` VALUES (1425709562853576706, 'TEST', 'TEST', '测试', '测试', '111', '2021-08-12 06:43:43');
COMMIT;

-- ----------------------------
-- Table structure for top_gateway_routes
-- ----------------------------
DROP TABLE IF EXISTS `top_gateway_routes`;
CREATE TABLE `top_gateway_routes` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `route_id` varchar(64) NOT NULL COMMENT '路由id',
  `route_uri` varchar(128) NOT NULL COMMENT '转发目标uri',
  `route_order` int NOT NULL DEFAULT '0' COMMENT '路由执行顺序',
  `predicates` varchar(200) NOT NULL COMMENT '访问路径',
  `filters` varchar(100) NOT NULL DEFAULT '1' COMMENT '过滤',
  `is_statistic` tinyint(1) DEFAULT '0' COMMENT '是否统计',
  `is_billing` tinyint(1) DEFAULT '0' COMMENT '是否计费',
  `is_ebl` tinyint(1) DEFAULT '0' COMMENT '是否启用',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '0未删，1删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1448540507088326658 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='网关路由表';

-- ----------------------------
-- Records of top_gateway_routes
-- ----------------------------
BEGIN;
INSERT INTO `top_gateway_routes` VALUES (1412634695491194174, 'top-resources', 'lb://top-resources', 0, '/res/**', '1', 0, 0, 0, 0, '2021-08-04 08:24:15', '2021-08-24 08:43:10');
INSERT INTO `top_gateway_routes` VALUES (1423619612481514096, 'top-system', 'lb://top-system', 0, '/system/**', '1', 0, 0, 0, 0, '2021-08-04 08:24:39', '2021-08-24 08:43:11');
INSERT INTO `top_gateway_routes` VALUES (1424619412471812096, 'top-user', 'lb://top-user', 0, '/user/**', '1', 0, 0, 0, 0, '2021-08-09 06:31:51', '2021-08-24 08:43:12');
INSERT INTO `top_gateway_routes` VALUES (1427205975529742338, 'top-authorization', 'lb://top-authorization', 0, '/auth/**', '1', 0, 0, 0, 0, '2021-07-27 11:00:13', '2021-08-24 08:43:15');
INSERT INTO `top_gateway_routes` VALUES (1448540507088326657, 'top-member', 'lb://top-member', 0, '/member/**', '1', 0, 0, 0, 0, '2021-10-14 06:45:44', '2021-10-14 06:51:12');
COMMIT;

-- ----------------------------
-- Table structure for top_idea
-- ----------------------------
DROP TABLE IF EXISTS `top_idea`;
CREATE TABLE `top_idea` (
  `id` bigint NOT NULL COMMENT 'id',
  `question_desc` text COMMENT '问题描述',
  `contacts` varchar(255) DEFAULT NULL COMMENT '联系人',
  `status` tinyint DEFAULT '0' COMMENT '状态：0未处理，1已处理',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of top_idea
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for top_log
-- ----------------------------
DROP TABLE IF EXISTS `top_log`;
CREATE TABLE `top_log` (
  `id` bigint NOT NULL COMMENT '主键',
  `group` varchar(255) DEFAULT NULL COMMENT '组',
  `user_id` bigint DEFAULT NULL COMMENT '用户Id',
  `type` smallint DEFAULT NULL COMMENT '日志类型 1查询 2修改 3新增 4删除 5导出 6审核',
  `method` varchar(255) DEFAULT NULL COMMENT '方法',
  `params` longtext COMMENT '参数',
  `time` bigint DEFAULT NULL COMMENT '时间',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `agent` varchar(255) DEFAULT NULL,
  `url` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='系统日志';

-- ----------------------------
-- Records of top_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for top_member
-- ----------------------------
DROP TABLE IF EXISTS `top_member`;
CREATE TABLE `top_member` (
  `id` bigint NOT NULL COMMENT 'id',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `mobile` varchar(255) DEFAULT NULL COMMENT '电话',
  `country_code` varchar(255) DEFAULT NULL COMMENT '手机区号',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `sex` int DEFAULT '0' COMMENT '性别（0：女，1：男）',
  `age` int DEFAULT NULL COMMENT '年龄',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `status` int DEFAULT '0' COMMENT '是否可用（0：可用，1：不可用）',
  `is_deleted` int DEFAULT '0' COMMENT '是否删除（0：未删除，1：已删除）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of top_member
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for top_member_oauth
-- ----------------------------
DROP TABLE IF EXISTS `top_member_oauth`;
CREATE TABLE `top_member_oauth` (
  `id` bigint NOT NULL COMMENT 'id',
  `uuid` varchar(255) DEFAULT NULL COMMENT '第三方系统用户ID',
  `member_id` bigint DEFAULT NULL COMMENT '系统用户主键',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `nickname` varchar(255) DEFAULT NULL COMMENT '用户昵称',
  `avatar` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `blog` varchar(255) DEFAULT NULL COMMENT '用户网址',
  `company` varchar(255) DEFAULT NULL COMMENT '所在公司',
  `location` varchar(255) DEFAULT NULL COMMENT '位置',
  `email` varchar(255) DEFAULT NULL COMMENT '用户邮箱',
  `remark` varchar(255) DEFAULT NULL COMMENT '用户备注（各平台中的用户个人介绍）',
  `gender` varchar(255) DEFAULT NULL COMMENT '性别',
  `source` varchar(255) DEFAULT NULL COMMENT '用户来源',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '状态[0:未删除,1:删除]',
  `status` tinyint(1) DEFAULT '0' COMMENT '状态[0:正常,1:冻结]',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='用户第三方认证表';

-- ----------------------------
-- Records of top_member_oauth
-- ----------------------------
BEGIN;
INSERT INTO `top_member_oauth` VALUES (1419846737400565760, '5438964', 1419846737140518912, 'jchen_px-a', 'chen_sir', 'https://gitee.com/assets/no_portrait.png', NULL, NULL, NULL, NULL, '坚持不屑', 'UNKNOWN', 'GITEE', 0, 0, NULL, NULL);
INSERT INTO `top_member_oauth` VALUES (1421046168070262784, '9074945', 1421046167793438720, 'xu-xuangan', '许炫淦', 'https://portrait.gitee.com/uploads/avatars/user/3024/9074945_xu-xuangan_1620273367.png', NULL, NULL, NULL, NULL, '', 'UNKNOWN', 'GITEE', 1, 0, '2021-08-12 03:35:23', '2021-08-12 03:35:23');
COMMIT;

-- ----------------------------
-- Table structure for top_member_oauth_token
-- ----------------------------
DROP TABLE IF EXISTS `top_member_oauth_token`;
CREATE TABLE `top_member_oauth_token` (
  `id` bigint NOT NULL COMMENT 'id',
  `member_oauth_id` bigint DEFAULT NULL COMMENT '第三方用户id',
  `access_token` varchar(255) DEFAULT NULL COMMENT 'token',
  `expire_in` int DEFAULT NULL COMMENT '过期时间',
  `refresh_token` varchar(255) DEFAULT NULL COMMENT '刷新token',
  `scope` varchar(255) DEFAULT NULL COMMENT '平台授权范围',
  `source` varchar(255) DEFAULT NULL COMMENT '来源',
  `status` int DEFAULT '0' COMMENT '状态[0:正常使用,1:过期]',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='第三方客户端token表';

-- ----------------------------
-- Records of top_member_oauth_token
-- ----------------------------
BEGIN;
INSERT INTO `top_member_oauth_token` VALUES (1419846737425731584, NULL, 'ebc29215b4a8bd95568842bda4dd8634', 86400, '8d78d40ee18509f09a108a317645a215929b0a4b2acd530a75a23654889c079a', 'user_info', NULL, 0, NULL, NULL);
INSERT INTO `top_member_oauth_token` VALUES (1421039601774104576, NULL, '55ee2268cac01ec733073f7d4739c34d', 86400, '63892ec23fd745d33eaa8032105d69f2e9d8203a2a1eb16f1eadeec1b2fcd9f9', 'user_info', NULL, 0, '2021-07-30 09:26:57', '2021-07-30 09:26:57');
INSERT INTO `top_member_oauth_token` VALUES (1421041048376971264, NULL, '59f16155f0ba439ef623592a93415e89', 86400, '7ada558c4d77262725fd2c51095707e5b8601bbe51ce10097c47b6da4a485fce', 'user_info', NULL, 0, '2021-07-30 09:32:42', '2021-07-30 09:32:42');
INSERT INTO `top_member_oauth_token` VALUES (1421041348445868032, NULL, 'fec4f823f5bfb19a929916cd045bdd0e', 86400, '664f99b7cd302a6e9d3031eba720efcae17c0fffb842dc6cc73e67239a67b4a8', 'user_info', NULL, 0, '2021-07-30 09:33:54', '2021-07-30 09:33:54');
INSERT INTO `top_member_oauth_token` VALUES (1421045121968902144, NULL, '50e297d6134e5b68f4c75c27c5a29727', 86400, '0875e85f914759e7db03ab4c98c0059299c6c8716ef681dec3cc1616d90fc90d', 'user_info', NULL, 0, '2021-07-30 09:48:53', '2021-07-30 09:48:53');
INSERT INTO `top_member_oauth_token` VALUES (1421045562899304448, NULL, '09b7c6f5ea89529856c8a290acc9f1f7', 86400, 'b10eb7b60b1604d7ff1d5429adccf1b3855bfe8bfe04ac13c0bcd947b9b73eb1', 'user_info', NULL, 0, '2021-07-30 09:50:39', '2021-07-30 09:50:39');
INSERT INTO `top_member_oauth_token` VALUES (1421046168091234304, NULL, 'cb65cdd422ffbff9579884c897a9a549', 86400, 'a9b80a193e4ed3e592e2f5f74da27f77766cf262882505f9522e659e45574c09', 'user_info', NULL, 0, '2021-07-30 09:53:03', '2021-07-30 09:53:03');
COMMIT;

-- ----------------------------
-- Table structure for top_menu
-- ----------------------------
DROP TABLE IF EXISTS `top_menu`;
CREATE TABLE `top_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` bigint DEFAULT NULL COMMENT '上级菜单ID',
  `key` varchar(255) DEFAULT NULL COMMENT '上级菜单唯一KEY值（用户菜单的权限和后台权限的唯一标识）',
  `type` tinyint NOT NULL DEFAULT '2' COMMENT '类型 1-分类 2-节点',
  `name` varchar(64) NOT NULL COMMENT '名称',
  `desc` varchar(256) DEFAULT NULL COMMENT '描述',
  `icon` varchar(45) DEFAULT NULL COMMENT '图标\r\n图标',
  `target_url` varchar(128) DEFAULT NULL COMMENT '目标地址（前端地址）',
  `shows` int NOT NULL DEFAULT '0' COMMENT '是否展示（0：展示，1不展示）',
  `sort` int DEFAULT '1' COMMENT '排序索引',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '状态 0-无效； 1-有效；',
  `create_user` bigint DEFAULT NULL COMMENT '创建人',
  `update_user` bigint DEFAULT NULL COMMENT '修改人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uni_key` (`key`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1480807798173343746 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='系统菜单';

-- ----------------------------
-- Records of top_menu
-- ----------------------------
BEGIN;
INSERT INTO `top_menu` VALUES (1440596988700905473, 0, 'USER_CENTRE', 1, '管理员中心', '用户中心', 'el-icon-s-custom', 'admin', 0, 3, 0, NULL, NULL, '2021-09-22 08:41:01', '2022-01-07 09:09:28');
INSERT INTO `top_menu` VALUES (1440597704748290050, 1440596988700905473, 'USER_MANAGEMENT', 1, '管理员用户', '用户管理', 'el-icon-user', 'user', 0, 3, 0, NULL, NULL, '2021-09-22 08:43:52', '2022-01-07 09:09:31');
INSERT INTO `top_menu` VALUES (1440598049473941505, 1440596988700905473, 'ROLE_MANAGEMENT', 1, '角色管理', '角色管理', 'el-icon-s-custom', 'role', 0, 2, 0, NULL, NULL, '2021-09-22 08:45:14', '2022-01-07 09:09:35');
INSERT INTO `top_menu` VALUES (1440599840395612161, 1440596988700905473, 'MENU_MANAGEMENT', 1, '菜单管理', '菜单管理', 'el-icon-goods', 'menu', 0, 1, 0, NULL, NULL, '2021-09-22 08:52:21', '2022-01-07 09:09:37');
INSERT INTO `top_menu` VALUES (1440608241863348225, 1440597704748290050, 'USER_SAVE', 2, '用户新增', '用户新增', 'el-icon-circle-plus-outline', '/', 0, 1, 0, NULL, NULL, '2021-09-22 09:25:44', '2021-11-15 10:38:07');
INSERT INTO `top_menu` VALUES (1440610826057928706, 1440597704748290050, 'USER_EDIT', 2, '用户编辑', '', 'el-icon-brush', '/', 0, 1, 0, NULL, NULL, '2021-09-22 09:36:00', '2021-11-15 10:38:07');
INSERT INTO `top_menu` VALUES (1440611499289858050, 1440597704748290050, 'USER_QUERY', 2, '用户查询', '', 'el-icon-search', '/', 0, 1, 0, NULL, NULL, '2021-09-22 09:38:41', '2021-11-15 10:38:07');
INSERT INTO `top_menu` VALUES (1440611619498610689, 1440597704748290050, 'USER_DELETE', 2, '用户删除', '', 'el-icon-delete', '/', 0, 1, 0, NULL, NULL, '2021-09-22 09:39:10', '2021-11-15 10:38:07');
INSERT INTO `top_menu` VALUES (1440612273088614401, 1440597704748290050, 'USER_FROZEN', 2, '用户冻结', '用户冻结', 'el-icon-ice-cream-square', '/', 0, 1, 0, NULL, NULL, '2021-09-22 09:41:45', '2021-11-15 10:38:07');
INSERT INTO `top_menu` VALUES (1440614186165202946, 1440598049473941505, 'ROLE_SAVE', 2, '角色新增', '', 'el-icon-circle-plus-outline', '/', 0, 1, 0, NULL, NULL, '2021-09-22 09:49:21', '2021-11-15 10:38:07');
INSERT INTO `top_menu` VALUES (1440614283401752578, 1440598049473941505, 'ROLE_QUERY', 2, '角色查询', '', 'el-icon-search', '/', 0, 1, 0, NULL, NULL, '2021-09-22 09:49:45', '2021-11-15 10:38:07');
INSERT INTO `top_menu` VALUES (1440614441254383617, 1440598049473941505, 'ROLE_EDIT', 2, '角色编辑', '', 'el-icon-brush', '/', 0, 1, 0, NULL, NULL, '2021-09-22 09:50:22', '2021-11-15 10:38:07');
INSERT INTO `top_menu` VALUES (1440615032588333057, 1440598049473941505, 'ROLE_DELETE', 2, '角色删除', '', 'el-icon-delete', '/', 0, 1, 0, NULL, NULL, '2021-09-22 09:52:43', '2021-11-15 10:38:07');
INSERT INTO `top_menu` VALUES (1440615330824318977, 1440598049473941505, 'ROLE_AUTHORIZE', 2, '角色授权', '', 'el-icon-warning', '/', 0, 1, 0, NULL, NULL, '2021-09-22 09:53:54', '2021-11-15 10:38:07');
INSERT INTO `top_menu` VALUES (1440616502364729346, 1440599840395612161, 'MENU_QUERY', 2, '菜单查询', '', 'el-icon-search', '/', 0, 1, 0, NULL, NULL, '2021-09-22 09:58:34', '2021-11-15 10:38:07');
INSERT INTO `top_menu` VALUES (1440617130591776770, 1440599840395612161, 'MENU_SAVE', 2, '菜单新增', '', 'el-icon-circle-plus-outline', '/', 0, 1, 0, NULL, NULL, '2021-09-22 10:01:03', '2021-11-15 10:38:07');
INSERT INTO `top_menu` VALUES (1440617435995828225, 1440599840395612161, 'MENU_EDIT', 2, '菜单编辑', '', 'el-icon-brush', '/', 0, 1, 0, NULL, NULL, '2021-09-22 10:02:16', '2021-11-15 10:38:07');
INSERT INTO `top_menu` VALUES (1440617784550879234, 1440599840395612161, 'MENU_DELETE', 2, '菜单删除', '', 'el-icon-delete', '/', 0, 1, 0, NULL, NULL, '2021-09-22 10:03:39', '2021-11-15 10:38:07');
INSERT INTO `top_menu` VALUES (1480481722872209409, 0, 'SYSTEM_CENTER', 1, '系统中心', '', 'el-icon-s-tools', 'system', 0, 1, 0, NULL, NULL, '2022-01-10 10:08:43', '2022-01-10 10:08:43');
INSERT INTO `top_menu` VALUES (1480481824395337729, 1480481722872209409, 'CONFIG_CENTER', 1, '配置中心', '', 'el-icon-setting', 'config', 0, 1, 0, NULL, NULL, '2022-01-10 10:09:07', '2022-01-10 10:09:07');
INSERT INTO `top_menu` VALUES (1480481825322278914, 1480481824395337729, 'CONFIG_QUERY', 2, '配置中心查询', NULL, 'el-icon-search', NULL, 0, 1, 0, NULL, NULL, '2022-01-10 10:09:07', '2022-01-10 10:12:54');
INSERT INTO `top_menu` VALUES (1480481825347444737, 1480481824395337729, 'CONFIG_SAVE', 2, '配置中心新增', NULL, 'el-icon-circle-plus-outline', NULL, 0, 1, 0, NULL, NULL, '2022-01-10 10:09:07', '2022-01-10 10:12:49');
INSERT INTO `top_menu` VALUES (1480481825351639041, 1480481824395337729, 'CONFIG_EDIT', 2, '配置中心修改', NULL, 'el-icon-brush', NULL, 0, 1, 0, NULL, NULL, '2022-01-10 10:09:07', '2022-01-10 10:12:44');
INSERT INTO `top_menu` VALUES (1480481825351639042, 1480481824395337729, 'CONFIG_DELETE', 2, '配置中心删除', NULL, 'el-icon-delete', NULL, 0, 1, 0, NULL, NULL, '2022-01-10 10:09:07', '2022-01-10 10:12:39');
INSERT INTO `top_menu` VALUES (1480482613167759362, 1480481722872209409, 'ROUTER_CENTER', 1, '路由中心', '', 'el-icon-s-help', 'router', 0, 1, 0, NULL, NULL, '2022-01-10 10:12:15', '2022-01-10 10:12:15');
INSERT INTO `top_menu` VALUES (1480482613964677121, 1480482613167759362, 'ROUTER_QUERY', 2, '路由中心查询', NULL, 'el-icon-search', NULL, 0, 1, 0, NULL, NULL, '2022-01-10 10:12:15', '2022-01-10 10:12:15');
INSERT INTO `top_menu` VALUES (1480482613968871425, 1480482613167759362, 'ROUTER_SAVE', 2, '路由中心新增', NULL, 'el-icon-circle-plus-outline', NULL, 0, 1, 0, NULL, NULL, '2022-01-10 10:12:15', '2022-01-10 10:12:15');
INSERT INTO `top_menu` VALUES (1480482613973065730, 1480482613167759362, 'ROUTER_EDIT', 2, '路由中心修改', NULL, 'el-icon-brush', NULL, 0, 1, 0, NULL, NULL, '2022-01-10 10:12:15', '2022-01-10 10:12:15');
INSERT INTO `top_menu` VALUES (1480482613973065731, 1480482613167759362, 'ROUTER_DELETE', 2, '路由中心删除', NULL, 'el-icon-delete', NULL, 0, 1, 0, NULL, NULL, '2022-01-10 10:12:15', '2022-01-10 10:12:15');
INSERT INTO `top_menu` VALUES (1480802088492474370, 1480481722872209409, 'LOG_CENTER', 1, '日志中心', '', 'el-icon-document', 'log', 0, 1, 0, NULL, NULL, '2022-01-11 07:21:44', '2022-01-11 07:21:44');
INSERT INTO `top_menu` VALUES (1480802089331335169, 1480802088492474370, 'LOG_QUERY', 2, '日志中心查询', NULL, 'el-icon-search', NULL, 0, 1, 0, NULL, NULL, '2022-01-11 07:21:44', '2022-01-11 07:21:44');
INSERT INTO `top_menu` VALUES (1480802089373278209, 1480802088492474370, 'LOG_SAVE', 2, '日志中心新增', NULL, 'el-icon-circle-plus-outline', NULL, 0, 1, 0, NULL, NULL, '2022-01-11 07:21:44', '2022-01-11 07:21:44');
INSERT INTO `top_menu` VALUES (1480802089381666817, 1480802088492474370, 'LOG_EDIT', 2, '日志中心修改', NULL, 'el-icon-brush', NULL, 0, 1, 0, NULL, NULL, '2022-01-11 07:21:44', '2022-01-11 07:21:44');
INSERT INTO `top_menu` VALUES (1480802089390055426, 1480802088492474370, 'LOG_DELETE', 2, '日志中心删除', NULL, 'el-icon-delete', NULL, 0, 1, 0, NULL, NULL, '2022-01-11 07:21:44', '2022-01-11 07:21:44');
INSERT INTO `top_menu` VALUES (1480802474322305025, 1480481722872209409, 'JOB_CENTER', 1, '定时任务', '', 'el-icon-attract', 'jobx', 0, 1, 0, NULL, NULL, '2022-01-11 07:23:16', '2022-01-11 07:24:49');
INSERT INTO `top_menu` VALUES (1480802475077279745, 1480802474322305025, 'JOB_QUERY', 2, '定时任务查询', NULL, 'el-icon-search', NULL, 0, 1, 0, NULL, NULL, '2022-01-11 07:23:16', '2022-01-11 07:23:16');
INSERT INTO `top_menu` VALUES (1480802475085668353, 1480802474322305025, 'JOB_SAVE', 2, '定时任务新增', NULL, 'el-icon-circle-plus-outline', NULL, 0, 1, 0, NULL, NULL, '2022-01-11 07:23:16', '2022-01-11 07:23:16');
INSERT INTO `top_menu` VALUES (1480802475094056962, 1480802474322305025, 'JOB_EDIT', 2, '定时任务修改', NULL, 'el-icon-brush', NULL, 0, 1, 0, NULL, NULL, '2022-01-11 07:23:16', '2022-01-11 07:23:16');
INSERT INTO `top_menu` VALUES (1480802475098251266, 1480802474322305025, 'JOB_DELETE', 2, '定时任务删除', NULL, 'el-icon-delete', NULL, 0, 1, 0, NULL, NULL, '2022-01-11 07:23:16', '2022-01-11 07:23:16');
INSERT INTO `top_menu` VALUES (1480807797263179778, 1480481722872209409, 'AUTO_CODE', 1, '自动代码', '', 'el-icon-magic-stick', 'auto_code', 0, 1, 0, NULL, NULL, '2022-01-11 07:44:25', '2022-01-11 07:44:25');
INSERT INTO `top_menu` VALUES (1480807798143983618, 1480807797263179778, 'AUTO_QUERY', 2, '自动代码查询', NULL, 'el-icon-search', NULL, 0, 1, 0, NULL, NULL, '2022-01-11 07:44:25', '2022-01-11 07:44:25');
INSERT INTO `top_menu` VALUES (1480807798169149442, 1480807797263179778, 'AUTO_SAVE', 2, '自动代码新增', NULL, 'el-icon-circle-plus-outline', NULL, 0, 1, 0, NULL, NULL, '2022-01-11 07:44:25', '2022-01-11 07:44:25');
INSERT INTO `top_menu` VALUES (1480807798169149443, 1480807797263179778, 'AUTO_EDIT', 2, '自动代码修改', NULL, 'el-icon-brush', NULL, 0, 1, 0, NULL, NULL, '2022-01-11 07:44:25', '2022-01-11 07:44:25');
INSERT INTO `top_menu` VALUES (1480807798173343745, 1480807797263179778, 'AUTO_DELETE', 2, '自动代码删除', NULL, 'el-icon-delete', NULL, 0, 1, 0, NULL, NULL, '2022-01-11 07:44:25', '2022-01-11 07:44:25');
COMMIT;

-- ----------------------------
-- Table structure for top_oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `top_oauth_client_details`;
CREATE TABLE `top_oauth_client_details` (
  `client_id` varchar(255) NOT NULL COMMENT '用于唯一标识每一个客户端',
  `resource_ids` varchar(255) DEFAULT NULL COMMENT '客户端所能访问的资源id集合,多个资源时用逗号(,)分隔',
  `client_secret` varchar(255) DEFAULT NULL COMMENT '用于指定客户端(client)的访问密匙',
  `client_secret_name` varchar(45) DEFAULT NULL,
  `scope` varchar(255) DEFAULT NULL COMMENT '指定客户端申请的权限范围',
  `authorized_grant_types` varchar(255) DEFAULT NULL COMMENT '指定客户端支持的grant_type（自定义时请在这里添加）',
  `web_server_redirect_uri` varchar(255) DEFAULT NULL COMMENT '客户端的重定向URI,可为空',
  `authorities` varchar(255) DEFAULT NULL COMMENT '指定客户端所拥有的Spring Security的权限值',
  `access_token_validity` int DEFAULT NULL COMMENT '设定客户端的access_token的有效时间值(单位:秒)',
  `refresh_token_validity` int DEFAULT NULL COMMENT '设定客户端的refresh_token的有效时间值(单位:秒)',
  `additional_information` varchar(4096) DEFAULT NULL COMMENT '预留的字段',
  `autoapprove` varchar(255) DEFAULT NULL COMMENT '设置用户是否自动Approval操作',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='客户端表';

-- ----------------------------
-- Records of top_oauth_client_details
-- ----------------------------
BEGIN;
INSERT INTO `top_oauth_client_details` VALUES ('top-api', 'top-authorization,top-gateway,top-user,top-resources,top-system,top-member,oauth2-resource', '$2a$10$WgOxjvMnK/hK2cXVll.E.en77eqwz81bg3e60N0HLZVXnOYk75SwK', 'top-secret', 'all', 'password,refresh_token,direct', NULL, NULL, 604800, 2592000, NULL, NULL, '2021-12-31 06:52:55');
COMMIT;

-- ----------------------------
-- Table structure for top_role
-- ----------------------------
DROP TABLE IF EXISTS `top_role`;
CREATE TABLE `top_role` (
  `id` bigint NOT NULL COMMENT '主键',
  `name` varchar(32) NOT NULL COMMENT '名称',
  `code` varchar(32) NOT NULL COMMENT '代码',
  `description` varchar(128) DEFAULT NULL COMMENT '描述',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '状态[0:未删除,1:删除]',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '状态[0:正常,1:冻结]',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` bigint DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `update_user` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='角色';

-- ----------------------------
-- Records of top_role
-- ----------------------------
BEGIN;
INSERT INTO `top_role` VALUES (1421018429334392833, '管理员', 'admin', '一切权限的拥有者(不可以删除！！！！！！！)', 0, 0, '2021-07-30 08:02:49', 111, '2021-07-30 08:41:28', 1);
INSERT INTO `top_role` VALUES (1421023105614311426, '普通用户', 'ordinary', '基础权限拥有者(不可以删除！！！！！！！)', 0, 0, '2021-07-30 08:21:24', 111, '2021-07-30 08:21:24', 1);
INSERT INTO `top_role` VALUES (1476873633778470913, 'test', 'test', 'test', 1, 0, '2021-12-31 11:11:28', 1450543146516761602, '2021-12-31 11:11:54', NULL);
INSERT INTO `top_role` VALUES (1476873792142807041, 'test', 'test', 'test', 1, 0, '2021-12-31 11:12:06', 1450543146516761602, '2021-12-31 11:12:25', NULL);
INSERT INTO `top_role` VALUES (1476874302530883585, '324', '4234', '234', 1, 0, '2021-12-31 11:14:07', 1450543146516761602, '2021-12-31 11:14:17', NULL);
INSERT INTO `top_role` VALUES (1476875117815500802, 'fsff', 'dsfsdf4324234', 'dsf', 1, 0, '2021-12-31 11:17:22', 1450543146516761602, '2021-12-31 11:20:19', 1450543146516761602);
COMMIT;

-- ----------------------------
-- Table structure for top_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `top_role_menu`;
CREATE TABLE `top_role_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint DEFAULT NULL,
  `menu_id` bigint DEFAULT NULL,
  `create_user` bigint DEFAULT NULL COMMENT '创建人',
  `update_user` bigint DEFAULT NULL COMMENT '修改人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1480807798815072258 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='角色菜单';

-- ----------------------------
-- Records of top_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `top_role_menu` VALUES (1479046251746979842, 1421023105614311426, 1468841014277099521, 1450543146516761602, NULL, '2022-01-06 11:04:40', '2022-01-06 11:04:40');
INSERT INTO `top_role_menu` VALUES (1479046251763757057, 1421023105614311426, 1468841214605447170, 1450543146516761602, NULL, '2022-01-06 11:04:40', '2022-01-06 11:04:40');
INSERT INTO `top_role_menu` VALUES (1479046251763757058, 1421023105614311426, 1468842174744547330, 1450543146516761602, NULL, '2022-01-06 11:04:40', '2022-01-06 11:04:40');
INSERT INTO `top_role_menu` VALUES (1479046251767951362, 1421023105614311426, 1468842051868217345, 1450543146516761602, NULL, '2022-01-06 11:04:40', '2022-01-06 11:04:40');
INSERT INTO `top_role_menu` VALUES (1480480776452677633, 1421018429334392833, 1440596988700905473, 1450543146516761602, NULL, '2022-01-10 10:04:58', '2022-01-10 10:04:58');
INSERT INTO `top_role_menu` VALUES (1480480776465260546, 1421018429334392833, 1440599840395612161, 1450543146516761602, NULL, '2022-01-10 10:04:58', '2022-01-10 10:04:58');
INSERT INTO `top_role_menu` VALUES (1480480776469454850, 1421018429334392833, 1440617784550879234, 1450543146516761602, NULL, '2022-01-10 10:04:58', '2022-01-10 10:04:58');
INSERT INTO `top_role_menu` VALUES (1480480776469454851, 1421018429334392833, 1440617435995828225, 1450543146516761602, NULL, '2022-01-10 10:04:58', '2022-01-10 10:04:58');
INSERT INTO `top_role_menu` VALUES (1480480776473649154, 1421018429334392833, 1440617130591776770, 1450543146516761602, NULL, '2022-01-10 10:04:58', '2022-01-10 10:04:58');
INSERT INTO `top_role_menu` VALUES (1480480776477843457, 1421018429334392833, 1440616502364729346, 1450543146516761602, NULL, '2022-01-10 10:04:58', '2022-01-10 10:04:58');
INSERT INTO `top_role_menu` VALUES (1480480776486232066, 1421018429334392833, 1440598049473941505, 1450543146516761602, NULL, '2022-01-10 10:04:58', '2022-01-10 10:04:58');
INSERT INTO `top_role_menu` VALUES (1480480776490426370, 1421018429334392833, 1440615330824318977, 1450543146516761602, NULL, '2022-01-10 10:04:58', '2022-01-10 10:04:58');
INSERT INTO `top_role_menu` VALUES (1480480776494620674, 1421018429334392833, 1440615032588333057, 1450543146516761602, NULL, '2022-01-10 10:04:58', '2022-01-10 10:04:58');
INSERT INTO `top_role_menu` VALUES (1480480776498814978, 1421018429334392833, 1440614441254383617, 1450543146516761602, NULL, '2022-01-10 10:04:58', '2022-01-10 10:04:58');
INSERT INTO `top_role_menu` VALUES (1480480776503009282, 1421018429334392833, 1440614283401752578, 1450543146516761602, NULL, '2022-01-10 10:04:58', '2022-01-10 10:04:58');
INSERT INTO `top_role_menu` VALUES (1480480776515592194, 1421018429334392833, 1440614186165202946, 1450543146516761602, NULL, '2022-01-10 10:04:58', '2022-01-10 10:04:58');
INSERT INTO `top_role_menu` VALUES (1480480776519786498, 1421018429334392833, 1440597704748290050, 1450543146516761602, NULL, '2022-01-10 10:04:58', '2022-01-10 10:04:58');
INSERT INTO `top_role_menu` VALUES (1480480776523980801, 1421018429334392833, 1440612273088614401, 1450543146516761602, NULL, '2022-01-10 10:04:58', '2022-01-10 10:04:58');
INSERT INTO `top_role_menu` VALUES (1480480776528175106, 1421018429334392833, 1440611619498610689, 1450543146516761602, NULL, '2022-01-10 10:04:58', '2022-01-10 10:04:58');
INSERT INTO `top_role_menu` VALUES (1480480776536563714, 1421018429334392833, 1440611499289858050, 1450543146516761602, NULL, '2022-01-10 10:04:58', '2022-01-10 10:04:58');
INSERT INTO `top_role_menu` VALUES (1480480776540758017, 1421018429334392833, 1440610826057928706, 1450543146516761602, NULL, '2022-01-10 10:04:58', '2022-01-10 10:04:58');
INSERT INTO `top_role_menu` VALUES (1480480776549146625, 1421018429334392833, 1440608241863348225, 1450543146516761602, NULL, '2022-01-10 10:04:58', '2022-01-10 10:04:58');
INSERT INTO `top_role_menu` VALUES (1480480898997657602, 1421018429334392833, 1480480898112659458, 1450543146516761602, NULL, '2022-01-10 10:05:27', '2022-01-10 10:05:27');
INSERT INTO `top_role_menu` VALUES (1480480990722891778, 1421018429334392833, 1480480989976305666, 1450543146516761602, NULL, '2022-01-10 10:05:49', '2022-01-10 10:05:49');
INSERT INTO `top_role_menu` VALUES (1480481723610406913, 1421018429334392833, 1480481722872209409, 1450543146516761602, NULL, '2022-01-10 10:08:44', '2022-01-10 10:08:44');
INSERT INTO `top_role_menu` VALUES (1480481825267752961, 1421018429334392833, 1480481824395337729, 1450543146516761602, NULL, '2022-01-10 10:09:08', '2022-01-10 10:09:08');
INSERT INTO `top_role_menu` VALUES (1480481826123390978, 1421018429334392833, 1480481825322278914, 1450543146516761602, NULL, '2022-01-10 10:09:08', '2022-01-10 10:09:08');
INSERT INTO `top_role_menu` VALUES (1480481826131779586, 1421018429334392833, 1480481825347444737, 1450543146516761602, NULL, '2022-01-10 10:09:08', '2022-01-10 10:09:08');
INSERT INTO `top_role_menu` VALUES (1480481826131779587, 1421018429334392833, 1480481825351639041, 1450543146516761602, NULL, '2022-01-10 10:09:08', '2022-01-10 10:09:08');
INSERT INTO `top_role_menu` VALUES (1480481826131779588, 1421018429334392833, 1480481825351639042, 1450543146516761602, NULL, '2022-01-10 10:09:08', '2022-01-10 10:09:08');
INSERT INTO `top_role_menu` VALUES (1480482613943705601, 1421018429334392833, 1480482613167759362, 1450543146516761602, NULL, '2022-01-10 10:12:16', '2022-01-10 10:12:16');
INSERT INTO `top_role_menu` VALUES (1480482614690291714, 1421018429334392833, 1480482613964677121, 1450543146516761602, NULL, '2022-01-10 10:12:16', '2022-01-10 10:12:16');
INSERT INTO `top_role_menu` VALUES (1480482614698680321, 1421018429334392833, 1480482613968871425, 1450543146516761602, NULL, '2022-01-10 10:12:16', '2022-01-10 10:12:16');
INSERT INTO `top_role_menu` VALUES (1480482614698680322, 1421018429334392833, 1480482613973065730, 1450543146516761602, NULL, '2022-01-10 10:12:16', '2022-01-10 10:12:16');
INSERT INTO `top_role_menu` VALUES (1480482614702874625, 1421018429334392833, 1480482613973065731, 1450543146516761602, NULL, '2022-01-10 10:12:16', '2022-01-10 10:12:16');
INSERT INTO `top_role_menu` VALUES (1480802089436192769, 1421018429334392833, 1480802088492474370, 1450543146516761602, NULL, '2022-01-11 07:21:45', '2022-01-11 07:21:45');
INSERT INTO `top_role_menu` VALUES (1480802090652540929, 1421018429334392833, 1480802089331335169, 1450543146516761602, NULL, '2022-01-11 07:21:45', '2022-01-11 07:21:45');
INSERT INTO `top_role_menu` VALUES (1480802090677706754, 1421018429334392833, 1480802089373278209, 1450543146516761602, NULL, '2022-01-11 07:21:45', '2022-01-11 07:21:45');
INSERT INTO `top_role_menu` VALUES (1480802090681901057, 1421018429334392833, 1480802089381666817, 1450543146516761602, NULL, '2022-01-11 07:21:45', '2022-01-11 07:21:45');
INSERT INTO `top_role_menu` VALUES (1480802090686095362, 1421018429334392833, 1480802089390055426, 1450543146516761602, NULL, '2022-01-11 07:21:45', '2022-01-11 07:21:45');
INSERT INTO `top_role_menu` VALUES (1480802475127611393, 1421018429334392833, 1480802474322305025, 1450543146516761602, NULL, '2022-01-11 07:23:17', '2022-01-11 07:23:17');
INSERT INTO `top_role_menu` VALUES (1480802475786117122, 1421018429334392833, 1480802475077279745, 1450543146516761602, NULL, '2022-01-11 07:23:17', '2022-01-11 07:23:17');
INSERT INTO `top_role_menu` VALUES (1480802475798700034, 1421018429334392833, 1480802475085668353, 1450543146516761602, NULL, '2022-01-11 07:23:17', '2022-01-11 07:23:17');
INSERT INTO `top_role_menu` VALUES (1480802475819671553, 1421018429334392833, 1480802475094056962, 1450543146516761602, NULL, '2022-01-11 07:23:17', '2022-01-11 07:23:17');
INSERT INTO `top_role_menu` VALUES (1480802475823865858, 1421018429334392833, 1480802475098251266, 1450543146516761602, NULL, '2022-01-11 07:23:17', '2022-01-11 07:23:17');
INSERT INTO `top_role_menu` VALUES (1480807798081069058, 1421018429334392833, 1480807797263179778, 1450543146516761602, NULL, '2022-01-11 07:44:26', '2022-01-11 07:44:26');
INSERT INTO `top_role_menu` VALUES (1480807798802489345, 1421018429334392833, 1480807798143983618, 1450543146516761602, NULL, '2022-01-11 07:44:26', '2022-01-11 07:44:26');
INSERT INTO `top_role_menu` VALUES (1480807798810877953, 1421018429334392833, 1480807798169149442, 1450543146516761602, NULL, '2022-01-11 07:44:26', '2022-01-11 07:44:26');
INSERT INTO `top_role_menu` VALUES (1480807798810877954, 1421018429334392833, 1480807798169149443, 1450543146516761602, NULL, '2022-01-11 07:44:26', '2022-01-11 07:44:26');
INSERT INTO `top_role_menu` VALUES (1480807798815072257, 1421018429334392833, 1480807798173343745, 1450543146516761602, NULL, '2022-01-11 07:44:26', '2022-01-11 07:44:26');
COMMIT;

-- ----------------------------
-- Table structure for top_user
-- ----------------------------
DROP TABLE IF EXISTS `top_user`;
CREATE TABLE `top_user` (
  `id` bigint NOT NULL COMMENT 'id',
  `code` varchar(255) DEFAULT NULL COMMENT '编号',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `country_code` varchar(255) DEFAULT NULL COMMENT '国际电话区号',
  `mobile` varchar(255) DEFAULT NULL COMMENT '手机号',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `birthday` varchar(255) DEFAULT NULL COMMENT '生日',
  `sex` tinyint(1) DEFAULT '0' COMMENT '性别[0:女,1:男]',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '状态[0:未删除,1:删除]',
  `status` tinyint(1) DEFAULT '0' COMMENT '状态[0:正常,1:冻结]',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(255) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_user` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
-- Records of top_user
-- ----------------------------
BEGIN;
INSERT INTO `top_user` VALUES (1450543146516761602, 'code', 'admin', '$2a$10$qB.jxAPPyXuYJME5uUDcxO6kFyfdwZOc0a28m5YXbasWUb2WLgRy2', '+86', '15779459202', 'jchen_px@126.com', 'https://f4497f31.oss-cn-shenzhen.aliyuncs.com/touxiang.gif', NULL, 0, 0, 0, '2022-01-10 06:49:56', '0', '2022-01-10 06:49:56', '1450543146516761602');
INSERT INTO `top_user` VALUES (1480431440935108609, NULL, '333', '$2a$10$sW5TZo4d4.atYTANUjtB4.WKCGn6rWhE6mkLf5MTsGg3mYZSD1VW.', NULL, '333', '33', NULL, NULL, 0, 1, 1, '2022-01-10 06:49:06', '1450543146516761602', '2022-01-10 06:49:06', '1450543146516761602');
COMMIT;

-- ----------------------------
-- Table structure for top_user_role
-- ----------------------------
DROP TABLE IF EXISTS `top_user_role`;
CREATE TABLE `top_user_role` (
  `id` bigint NOT NULL COMMENT '主键',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '状态[0:未删除,1:删除]',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '状态[0:正常,1:冻结]',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` bigint DEFAULT NULL COMMENT '创建人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `update_user` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='用户角色配置';

-- ----------------------------
-- Records of top_user_role
-- ----------------------------
BEGIN;
INSERT INTO `top_user_role` VALUES (1438807020236087297, 1421018429334392833, 1450543146516761602, 1, 0, '2021-09-17 10:08:20', 1, '2021-12-31 11:05:04', NULL);
INSERT INTO `top_user_role` VALUES (1476856460787965953, 1421023105614311426, 1476856460632776705, 0, 0, '2021-12-31 10:03:14', 1450543146516761602, '2021-12-31 10:03:14', NULL);
INSERT INTO `top_user_role` VALUES (1476872025216770049, 1421018429334392833, 1450543146516761602, 1, 0, '2021-12-31 11:05:04', 1450543146516761602, '2021-12-31 11:05:12', NULL);
INSERT INTO `top_user_role` VALUES (1476872058607624194, 1421018429334392833, 1450543146516761602, 1, 0, '2021-12-31 11:05:12', 1450543146516761602, '2021-12-31 11:05:20', NULL);
INSERT INTO `top_user_role` VALUES (1476872090937319426, 1421018429334392833, 1450543146516761602, 1, 0, '2021-12-31 11:05:20', 1450543146516761602, '2021-12-31 11:06:38', NULL);
INSERT INTO `top_user_role` VALUES (1476872441350447106, 1421018429334392833, 1450543146516761602, 1, 0, '2021-12-31 11:06:44', 1450543146516761602, '2021-12-31 13:04:09', NULL);
INSERT INTO `top_user_role` VALUES (1476874058506276865, 1421023105614311426, 1476874054353915905, 0, 0, '2021-12-31 11:13:09', 1450543146516761602, '2021-12-31 11:13:09', NULL);
INSERT INTO `top_user_role` VALUES (1476876463746682881, 1421023105614311426, 1476876459481075713, 0, 0, '2021-12-31 11:22:43', 1450543146516761602, '2021-12-31 11:22:43', NULL);
INSERT INTO `top_user_role` VALUES (1476876660421791746, 1421023105614311426, 1476876653346000898, 0, 0, '2021-12-31 11:23:30', 1450543146516761602, '2021-12-31 11:23:30', NULL);
INSERT INTO `top_user_role` VALUES (1476876866626359297, 1421023105614311426, 1476876861643526145, 1, 0, '2021-12-31 11:24:19', 1450543146516761602, '2021-12-31 11:24:41', NULL);
INSERT INTO `top_user_role` VALUES (1476876961551847426, 1421023105614311426, 1476876861643526145, 1, 0, '2021-12-31 11:24:41', 1450543146516761602, '2021-12-31 11:25:01', NULL);
INSERT INTO `top_user_role` VALUES (1476877045278543873, 1421023105614311426, 1476876861643526145, 0, 0, '2021-12-31 11:25:01', 1450543146516761602, '2021-12-31 11:25:01', NULL);
INSERT INTO `top_user_role` VALUES (1476901994760273921, 1421018429334392833, 1450543146516761602, 1, 0, '2021-12-31 13:04:10', 1450543146516761602, '2022-01-10 06:49:52', NULL);
INSERT INTO `top_user_role` VALUES (1480431441144823809, 1421018429334392833, 1480431440935108609, 1, 0, '2022-01-10 06:48:55', 1450543146516761602, '2022-01-10 06:49:00', NULL);
INSERT INTO `top_user_role` VALUES (1480431464309964801, 1421018429334392833, 1480431440935108609, 0, 0, '2022-01-10 06:49:01', 1450543146516761602, '2022-01-10 06:49:01', NULL);
INSERT INTO `top_user_role` VALUES (1480431680446644225, 1421018429334392833, 1450543146516761602, 1, 0, '2022-01-10 06:49:52', 1450543146516761602, '2022-01-10 06:49:56', NULL);
INSERT INTO `top_user_role` VALUES (1480431696250781698, 1421018429334392833, 1450543146516761602, 0, 0, '2022-01-10 06:49:56', 1450543146516761602, '2022-01-10 06:49:56', NULL);
COMMIT;

-- ----------------------------
-- Table structure for top_version_upgrade
-- ----------------------------
DROP TABLE IF EXISTS `top_version_upgrade`;
CREATE TABLE `top_version_upgrade` (
  `id` bigint unsigned NOT NULL,
  `app_id` bigint DEFAULT NULL COMMENT 'app外键id',
  `name` varchar(255) DEFAULT NULL COMMENT '版本名称',
  `version_id` smallint unsigned DEFAULT '0' COMMENT '大版本号id',
  `version_mini` mediumint unsigned DEFAULT '0' COMMENT '小版本号',
  `version_code` varchar(10) DEFAULT NULL COMMENT '版本标识 1.2',
  `type` tinyint unsigned DEFAULT NULL COMMENT '是否升级 1升级，0不升级，2强制升级',
  `apk_url` varchar(255) DEFAULT NULL COMMENT '安装包url',
  `upgrade_point` longtext COMMENT '升级提示',
  `status` tinyint(1) DEFAULT '0' COMMENT '状态[0:正常,1:停用]',
  `is_deleted` int DEFAULT '0' COMMENT '0未删，1删除''',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='app版本控制表';

-- ----------------------------
-- Records of top_version_upgrade
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
